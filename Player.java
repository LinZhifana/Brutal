package game;

import java.util.ArrayList;

public class Player {
    private static final int NUM_OF_STU = 20;
    private static final int NUM_OF_RES = 5;
    private static final int MAX_POINT = 10;
    private static final int TOTAL_POINT = 400;
    private static final int[] NUM_OF_EACH_STU_KIND = {15, 4, 1};
    private static final int VICTORY_CONDITIONS = 3;

    private Programme programme;
    private Gamers gamer;
    private ArrayList<Student> students = new ArrayList<Student>(NUM_OF_STU);
    private ArrayList<Student> reservists = new ArrayList<Student>(NUM_OF_RES);
    private ArrayList<ZoneName> zoneNames = new ArrayList<ZoneName>();

    private String createPrompt(Category cat) {
        StringBuffer prompt = new StringBuffer();
        for (Attribute a : Attribute.values()) {
            if (a == Attribute.CATEGORY) {
                prompt.append(a.name() + " : " + cat.name());
                prompt.append("\n");
            } else if (a == Attribute.STRATEGY) {
                prompt.append(a.name() + "(0-2) " + " : ");
                for (Strategy s : Strategy.values()) {
                    prompt.append(s.name() + "(" + s.ordinal() + ") ");
                }
                prompt.append("\n");
            } else if (a != Attribute.CREDITS_ECTS) {
                prompt.append(a.name() + "(0-" + MAX_POINT + ") ");
            }
        }
        return prompt.toString();
    }

    private ArrayList<ArrayList<Integer>> allocatePoints() {
        // 减去不用输入的 CREDITS_ECTS 点数
        int length = Attribute.values().length - 1;
        int total = TOTAL_POINT;
        ArrayList<ArrayList<Integer>> pointsArray = new ArrayList<ArrayList<Integer>>(NUM_OF_STU);
        for (int i = 0; i < NUM_OF_STU; i++) {
            //确定学生种类
            Category c;
            if (i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()])
                c = Category.ETUDIANT;
            else if (i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()]
                    + NUM_OF_EACH_STU_KIND[Category.ETUDIANT_ELITE.ordinal()])
                c = Category.ETUDIANT_ELITE;
            else
                c = Category.MAITRE_GOBI;
            System.out.println(createPrompt(c));
            //确定学生的策略
            System.out.println("Veuillez entrer la stratégie de l'étudiant " + (i + 1));
            int s = MainProcess.checkInput(1, 1, 0, Strategy.values().length - 1, false)[0];
            //确定学生的点数
            System.out.println("Veuillez attribuer des points à l'étudiant " + (i + 1) + "(cinq types)");
            System.out.println("Les points restants: " + total);
            int[] ps = MainProcess.checkInput(length - 2, length - 2, 0, MAX_POINT, false);
            //存入数组，判断是否越界
            ArrayList<Integer> points = new ArrayList<Integer>(length);
            points.add(c.ordinal());
            points.add(s);
            int psTotal = 0;
            for (int j = 0; j < ps.length; j++) {
                psTotal += ps[j];
                points.add(ps[j]);
            }
            if (total - psTotal < 0) {
                i--;
                System.out.println("Pas assez de points restants!");
                continue;
            } else {
                total -= psTotal;
            }
            pointsArray.add(points);
        }
        return pointsArray;
    }

    public Player(Gamers gamer, Programme programme) {
        this.gamer = gamer;
        this.programme = programme;
        ArrayList<ArrayList<Integer>> points = allocatePoints();
        for (ArrayList<Integer> p : points) {
            this.students.add(new Student(p));
            System.out.println((new Student(p)).toString());
        }
    }

    public void setReservists() {
        System.out.println("Veuillez sélectionner votre RESERVIST");
        //打印学生
        for (int i = 0; i < this.students.size(); i++) {
            System.out.println((i + 1) + " : " + this.students.get(i).toString());
        }
        int[] ordinals = MainProcess.checkInput(NUM_OF_RES, NUM_OF_RES, 1, NUM_OF_STU, false);
        for (int i = 0; i < ordinals.length; i++) {
            Student s = this.students.remove(ordinals[i] - 1 - i);
            s.setReservist();
            this.reservists.add(s);
        }
    }

    public void addZoneNames(ZoneName zoneName) {
        this.zoneNames.add(zoneName);
    }

    boolean isWin() {
        return this.zoneNames.size() >= VICTORY_CONDITIONS ? true : false;
    }

    private void assignStudents(Zone zone, ArrayList<Student> students, int lengthMax, boolean isEmpty) {
        //打印学生
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + " : " + students.get(i).toString());
        }
        //确认分配的学生
        int[] ordinals = MainProcess.checkInput(1, lengthMax, 1, students.size(), isEmpty);
        //转移学生
        if (ordinals != null)
            for (int i = 0; i < ordinals.length; i++) {
                Student s = students.remove(ordinals[i] - 1 - i);
                zone.addStudent(s, this.gamer);
            }
    }

    public void assignStudentsToZone(Zone zone, boolean isEmpty) {
        System.out.println("Veuillez entrer les étudiants affectés à " + zone.getZoneName().getName());
        assignStudents(zone, this.students, this.students.size(), isEmpty);
    }

    public void assignReservistsToZone(Zone zone, boolean isEmpty) {
        System.out.println("Veuillez entrer les reservists affectés à " + zone.getZoneName().getName());
        assignStudents(zone, this.reservists, this.reservists.size(), isEmpty);
    }

    public void assignGarrisonToZone(Zone zone) {
        System.out.println("Veuillez entrer les étudiants qui restent sur la zone contrôlée affectés à " + zone.getZoneName().getName());
        assignStudents(zone, this.students, 1, false);
    }

    public void addStudents(Student student) {
        if (student.isReservist()) {
            this.reservists.add(student);
        } else {
            this.students.add(student);
        }
    }

    public boolean hasStudents() {
        return !this.students.isEmpty();
    }

    public boolean hasReservists() {
        return !this.reservists.isEmpty();
    }

    public Gamers getGamer() {
        return this.gamer;
    }

    public String toString() {
        StringBuffer str = new StringBuffer("PLAYER :" + this.gamer.name() + "\n");
        str.append("Students : \n");
        int i = 0;
        for (Student s : this.students) {
            str.append((++i) + " : " + s.toString() + "\n");
        }
        str.append("Reservists : \n");
        for (Student s : this.reservists) {
            str.append((++i) + " : " + s.toString() + "\n");
        }
        return str.toString();
    }

}
