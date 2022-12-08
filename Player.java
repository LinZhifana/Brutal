package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static final int NUM_OF_STU = 3;
    private static final int NUM_OF_RES = 2;
    private static final int MAX_POINT = 10;
    private static final int TOTAL_POINT = 100;
    private static final int[] NUM_OF_EACH_STU_KIND = {1, 1, 1};

    private static final int VICTORY_CONDITIONS = 3;

    private Programme programme;
    private Gamers gamer;
    private ArrayList<Student> students = new ArrayList<Student>(NUM_OF_STU);
    private ArrayList<ZoneName> zoneNames = new ArrayList<ZoneName>();

    private String createPrompt(Category cat) {
        StringBuffer prompt = new StringBuffer();
        for (Attribute a : Attribute.values()) {
            if (a == Attribute.CATEGORY) {
                prompt.append(a.name() + " : " + cat.name());
                prompt.append("\n");
            } else if (a == Attribute.STRATEGY) {
                prompt.append(a.name() + "(0-3) " + " : ");
                for (Strategy s : Strategy.values()) {
                    prompt.append(s.name() + "(" + s.ordinal() + ") ");
                }
                prompt.append("\n");
            } else if (a != Attribute.CREDITS_ECTS) {
                prompt.append(a.name() + "(0-10) ");
            }
        }
        return prompt.toString();
    }

    private ArrayList<ArrayList<Integer>> allocatePoints() {
        int length = Attribute.values().length - 1;
        int total = TOTAL_POINT;
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>(NUM_OF_STU);
        for (int i = 0; i < NUM_OF_STU; i++) {
            int[] points = new int[length];
            Category c;
            if (i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()]) {
                c = Category.ETUDIANT;
            } else if (i < NUM_OF_EACH_STU_KIND[Category.ETUDIANT.ordinal()] + NUM_OF_EACH_STU_KIND[Category.ETUDIANT_ELITE.ordinal()]) {
                c = Category.ETUDIANT_ELITE;
            } else {
                c = Category.MAITRE_GOBI;
            }
            System.out.println(createPrompt(c));
            System.out.println("剩余点数" + total);
            Scanner scanner = new Scanner(System.in);
            boolean isEnterWrong = false;
            String[] userPointsStr;
            do {
                userPointsStr = scanner.nextLine().split(",");
                if (userPointsStr.length != length - 1) {
                    isEnterWrong = true;
                    System.out.println("数组长度错误");
                    continue;
                } else {
                    isEnterWrong = false;
                }
                int userPointsStrIndex = 0;
                for (Attribute a : Attribute.values()) {
                    if (a == Attribute.CATEGORY) {
                        points[a.ordinal()] = c.ordinal();
                    } else if (a == Attribute.STRATEGY) {
                        int p = Integer.parseInt(userPointsStr[userPointsStrIndex++]);
                        if (p > Strategy.values().length || p < 0) {
                            System.out.println(a.name() + " 范围越界");
                            isEnterWrong = true;
                            break;
                        } else {
                            points[a.ordinal()] = p;
                        }
                    } else if (a != Attribute.CREDITS_ECTS) {
                        int p = Integer.parseInt(userPointsStr[userPointsStrIndex++]);
                        if (p > MAX_POINT || p < 0) {
                            System.out.println(a.name() + " 范围越界");
                            isEnterWrong = true;
                            break;
                        } else {
                            points[a.ordinal()] = p;
                        }
                    }
                    isEnterWrong = false;
                }
            } while (isEnterWrong);
            ArrayList<Integer> ps = new ArrayList<Integer>(length);
            for (int j = 0; j < length; j++) {
                if (j > Attribute.STRATEGY.ordinal()) {
                    total -= points[j];
                    if (total < 0) {
                        i--;
                        System.out.println("点数不足");
                        continue;
                    }
                }
                ps.add(points[j]);
            }
            ret.add(ps);
        }
        return ret;
    }

    private boolean findDuplicateInArray(String[] a) {
        boolean ret = false;
        for (int j = 0; j < a.length; j++) {
            for (int k = j + 1; k < a.length; k++) {
                if (a[j] == a[k]) {
                    ret = true;
                }
            }
            if (ret)
                break;
            ret = false;
        }
        return ret;
    }

    public Player(Gamers gamer, Programme programme) {
        this.gamer = gamer;
        this.programme = programme;
        ArrayList<ArrayList<Integer>> points = allocatePoints();
        for (ArrayList<Integer> p : points) {
            this.students.add(new Student(p));
        }
    }

    public Player(ArrayList<ArrayList<Integer>> points) {
        for (ArrayList<Integer> p : points) {
            this.students.add(new Student(p));
        }
    }

    public void delStudent(Student s) {
        this.students.remove(s);
    }

    private String[] checkInput(int arrayLength, int max, int min){
        Scanner scanner = new Scanner(System.in);
        boolean isEnterWrong = false;
        String[] userPointsStr;

        do {
            userPointsStr = scanner.nextLine().split(",");
            if (userPointsStr.length > NUM_OF_STU) {
                System.out.println("数组长度错误" + NUM_OF_RES);
                isEnterWrong = true;
                continue;
            } else {
                isEnterWrong = false;
            }
            if (findDuplicateInArray(userPointsStr)) {
                System.out.println("有重复元素");
                isEnterWrong = true;
                continue;
            }

            for (String str : userPointsStr) {
                int num = Integer.parseInt(str);
                if (num > NUM_OF_STU || num <= 0 || reservists.contains(num)) {
                    System.out.println("数字不对(包含预备役)" + num);
                    isEnterWrong = true;
                    break;
                }
                isEnterWrong = false;
            }
        } while (isEnterWrong);

        return userPointsStr;
    }

    public void distributeStudent(Zone zone) {
        System.out.println(this.toString());
        //分配打印
        Scanner scanner = new Scanner(System.in);
        boolean isEnterWrong = false;
        String[] userPointsStr;

        ArrayList<Integer> reservists = new ArrayList<Integer>();
        for (int i = 0; i < NUM_OF_STU; i++) {
            if(this.students.get(i).isReservist()){
                reservists.add(i + 1);
            }
        }

        do {
            userPointsStr = scanner.nextLine().split(",");
            if (userPointsStr.length > NUM_OF_STU) {
                System.out.println("数组长度错误" + NUM_OF_RES);
                isEnterWrong = true;
                continue;
            } else {
                isEnterWrong = false;
            }
            if (findDuplicateInArray(userPointsStr)) {
                System.out.println("有重复元素");
                isEnterWrong = true;
                continue;
            }

            for (String str : userPointsStr) {
                int num = Integer.parseInt(str);
                if (num > NUM_OF_STU || num <= 0 || reservists.contains(num)) {
                    System.out.println("数字不对(包含预备役)" + num);
                    isEnterWrong = true;
                    break;
                }
                isEnterWrong = false;
            }
        } while (isEnterWrong);

        Student[] sArr = new Student[userPointsStr.length];
        for (int i = 0; i < userPointsStr.length; i++) {
            sArr[i] = this.students.get(Integer.parseInt(userPointsStr[i]) - 1);
        }
        for (Student s : sArr) {
            zone.addStudent(s, this.gamer);
            delStudent(s);
        }
    }

    public void distributeStudent(Zone zone, ArrayList<Student> students) {
        //分配打印
        Scanner scanner = new Scanner(System.in);
        boolean isEnterWrong = false;
        String[] userPointsStr;
        int numOfStu = students.size();
        ArrayList<Integer> reservists = new ArrayList<Integer>();
        for (int i = 0; i < numOfStu; i++) {
            if(students.get(i).isReservist()){
                reservists.add(i + 1);
            }
        }
        // 打印预备役

        do {
            userPointsStr = scanner.nextLine().split(",");
            if (userPointsStr.length > numOfStu) {
                System.out.println("数组长度错误" + NUM_OF_RES);
                isEnterWrong = true;
                continue;
            } else {
                isEnterWrong = false;
            }
            if (findDuplicateInArray(userPointsStr)) {
                System.out.println("有重复元素");
                isEnterWrong = true;
                continue;
            }

            for (String str : userPointsStr) {
                int num = Integer.parseInt(str);
                if (num > numOfStu || num <= 0 || reservists.contains(num)) {
                    System.out.println("数字不对(包含预备役)" + num);
                    isEnterWrong = true;
                    break;
                }
                isEnterWrong = false;
            }
        } while (isEnterWrong);

        Student[] sArr = new Student[userPointsStr.length];
        for (int i = 0; i < userPointsStr.length; i++) {
            sArr[i] = students.get(Integer.parseInt(userPointsStr[i]) - 1);
        }
        for (Student s : sArr) {
            zone.addStudent(s, this.gamer);
            students.remove(s);
        }
    }

    public void setReservist() {
        System.out.println("请选择预备役\n");
        System.out.println(this.toString());

        Scanner scanner = new Scanner(System.in);
        boolean isEnterWrong = false;
        String[] userPointsStr;

        do {
            userPointsStr = scanner.nextLine().split(",");
            if (userPointsStr.length != NUM_OF_RES) {
                System.out.println("数组长度错误" + NUM_OF_RES);
                isEnterWrong = true;
                continue;
            } else {
                isEnterWrong = false;
            }

            if (findDuplicateInArray(userPointsStr)) {
                System.out.println("有重复元素");
                isEnterWrong = true;
                continue;
            } else {
                isEnterWrong = false;
            }

            for (String str : userPointsStr) {
                int num = Integer.parseInt(str);
                if (num > NUM_OF_STU || num <= 0) {
                    System.out.println("数字不对" + NUM_OF_STU);
                    isEnterWrong = true;
                    break;
                }
                isEnterWrong = false;
            }
        } while (isEnterWrong);

        for (String str : userPointsStr) {
            this.students.get(Integer.parseInt(str) - 1).setReservist();
        }
    }

    public void distributeReservist(Zone zone){
        ArrayList<Integer> reservists = new ArrayList<Integer>();
        for (int i = 0; i < NUM_OF_STU; i++) {
            if(this.students.get(i).isReservist()){
                reservists.add(i + 1);
            }
        }

        if(reservists.size()>0){
            // 分配预备役输出提示
            Scanner scanner = new Scanner(System.in);
            boolean isEnterWrong = false;
            String[] userPointsStr;

            do {
                userPointsStr = scanner.nextLine().split(",");
                if (userPointsStr.length > reservists.size()) {
                    System.out.println("数组长度错误" + NUM_OF_RES);
                    isEnterWrong = true;
                    continue;
                } else {
                    isEnterWrong = false;
                }
                if (findDuplicateInArray(userPointsStr)) {
                    System.out.println("有重复元素");
                    isEnterWrong = true;
                    continue;
                }

                for (String str : userPointsStr) {
                    int num = Integer.parseInt(str);
                    if (!reservists.contains(num)) {
                        System.out.println("数字不对" + NUM_OF_STU);
                        isEnterWrong = true;
                        break;
                    }
                    isEnterWrong = false;
                }
            } while (isEnterWrong);

            Student[] sArr = new Student[userPointsStr.length];
            for (int i = 0; i < userPointsStr.length; i++) {
                sArr[i] = this.students.get(Integer.parseInt(userPointsStr[i]) - 1);
            }
            for (Student s : sArr) {
                zone.addStudent(s, this.gamer);
                delStudent(s);
            }
        }

    }

    public void redistribute(Zone zone, Zone[] zones){
        ArrayList<Student> students = null;
        if(this.gamer == Gamers.A){
            students = zone.getStudentsOfPlayerA();
        }else if(this.gamer == Gamers.B){
            students = zone.getStudentsOfPlayerB();
        }
        if(!students.isEmpty()){
            for (Student s: students) {
                System.out.println(s.toString());
            }
            StringBuffer prompt = new StringBuffer();
            for (Zone z:zones) {
                prompt.append(z.getZoneName().getName() + " ");
            }
            System.out.println(prompt.toString());


            //分配打印
            Scanner scanner = new Scanner(System.in);
            boolean isEnterWrong = false;
            String[] userPointsStr;
            int numOfStu = students.size();
            ArrayList<Integer> reservists = new ArrayList<Integer>();
            for (int i = 0; i < numOfStu; i++) {
                if(students.get(i).isReservist()){
                    reservists.add(i + 1);
                }
            }

            do {
                userPointsStr = scanner.nextLine().split(",");
                if (userPointsStr.length > numOfStu) {
                    System.out.println("数组长度错误" + NUM_OF_RES);
                    isEnterWrong = true;
                    continue;
                } else {
                    isEnterWrong = false;
                }
                if (findDuplicateInArray(userPointsStr)) {
                    System.out.println("有重复元素");
                    isEnterWrong = true;
                    continue;
                }

                for (String str : userPointsStr) {
                    int num = Integer.parseInt(str);
                    if (num > numOfStu || num <= 0 || reservists.contains(num)) {
                        System.out.println("数字不对(包含预备役)" + num);
                        isEnterWrong = true;
                        break;
                    }
                    isEnterWrong = false;
                }
            } while (isEnterWrong);
        }
    }

    public void addZoneNames(ZoneName zoneName){
        this.zoneNames.add(zoneName);
    }

    boolean isWin() {
        return this.zoneNames.size() >= VICTORY_CONDITIONS ? true : false;
    }

    public String toString() {
        StringBuffer str = new StringBuffer("PLAYER :" + this.gamer.name() + "\n");
        int i = 0;
        for (Student s : this.students) {
            str.append((++i) + " : " + s.toString() + "\n");
        }
        return str.toString();
    }

    public static void main(String args[]) {
        Player p = new Player(Gamers.A, Programme.GI);
        Zone z = new Zone(ZoneName.HI);
        p.setReservist();
        p.distributeStudent(z);
        System.out.println(z.toString());
    }
}
