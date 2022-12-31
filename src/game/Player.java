package game;

import java.util.ArrayList;
import java.util.Iterator;

public class Player implements Config {
    private String name;
    private Gamers gamer;
    private Programme programme;
    private ArrayList<ZoneName> zoneNames = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<Student>(NUM_OF_STU);
    private ArrayList<Student> reservists = new ArrayList<Student>(NUM_OF_RES);

    public Player(Gamers gamer) {
        this.gamer = gamer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gamers getGamer() {
        return gamer;
    }

    public void setGamer(Gamers gamer) {
        this.gamer = gamer;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getReservists() {
        return reservists;
    }

    public void setReservists(ArrayList<Student> reservists) {
        this.reservists = reservists;
    }

    public void addZoneNames(ZoneName zoneName) {
        this.zoneNames.add(zoneName);
    }

    public boolean isWin() {
        return this.zoneNames.size() >= VICTORY_CONDITIONS ? true : false;
    }

    public boolean hasStudents() {
        return !this.students.isEmpty();
    }

    public boolean hasReservists() {
        return !this.reservists.isEmpty();
    }

    public void addStudents(Student student) {
        if (student.isReservist()) {
            this.reservists.add(student);
        } else {
            this.students.add(student);
        }
    }

    private void assignStudents(Zone zone, ArrayList<Student> students) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getZoneName() == zone.getZoneName()) {
                zone.addStudent(s, this.gamer);
                students.remove(it);
            }
        }
    }

    public void assignStudentsToZone(Zone zone) {
        assignStudents(zone, this.students);
    }

    public void assignReservistsToZone(Zone zone) {
        assignStudents(zone, this.reservists);
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
