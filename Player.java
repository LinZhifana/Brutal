package student;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Programme programme;
    private ArrayList<Student> students;
    private ArrayList<ZoneName> zoneNames;

    private static int NUM_OF_STU = 2;
    private static int NUM_OF_RES = 1;

    private Strategy integer2Strategy(int i) {
        switch (i) {
            case 0:
                return Strategy.DEFENSIVE;
            case 1:
                return Strategy.OFFENSIVE;
            case 2:
                return Strategy.RANDOM;
        }
        return null;
    }

    public void allocatePoints(ArrayList<ArrayList<Integer>> points) {
        if (points.size() == NUM_OF_STU) {
            for (int i = 0; i < NUM_OF_STU; i++) {
                this.students.add(new Student(points.get(i)));
            }
        } else {
            //error
        }
    }

    public void selectReserve() {

    }

    public void selectStrategy() {
        for (int i = 0; i < NUM_OF_STU; i++) {
            System.out.println("Please enter the strategy [ DEFENSIVE(0), OFFENSIVE(1), RANDOM(2) ] : \n");
            Scanner s = new Scanner(System.in);
            int n = s.nextInt();
            while (n < 0 && n >= 3) {
                System.out.println("Please enter the strategy [ DEFENSIVE(0), OFFENSIVE(1), RANDOM(2) ] : \n");
                n = s.nextInt();
            }
            this.students.get(i).setStrategy(integer2Strategy(i));
        }
    }

    public Programme getProgramme() {
        return this.programme;
    }

    //    public int consulterECTS(Zone position){
//        return 0;
//    }
    public void repartirStudents() {

    }

    public void repartirReservistes() {

    }

    //    public void moveStudents(Zone position){
//
//    }
//    public void choisirStudentRester(Zone position){
//
//    }
    public void rechoisirStrategy(Student student, Strategy strategy) {
        student.setStrategy(strategy);
    }

    public void delStudent(Student student) {
        this.students.indexOf(student);
    }

    public void addZone(ZoneName zoneName) {
        this.zoneNames.add(zoneName);
    }

    public int getZoneNum() {
        return zoneNames.size();
    }
}
