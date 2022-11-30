package Brutal;

import java.util.ArrayList;
import java.util.Scanner;


public class Player {

    private static int NUM_OF_STU = 2;
    private static int NUM_OF_RES = 1;
    private static int MaxPoint = 2;
    private static int Max = 10;
    private Programme programme;
    private ArrayList<Student> students = new ArrayList<Student>(NUM_OF_STU);


    private ArrayList<ZoneName> zoneNames;




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

    private Attribute integer2Attribute(Integer i) {
        switch (i.intValue()) {
            case 0:
                return Attribute.CATEGORY;
            case 1:
                return Attribute.RESISTANCE;
            case 2:
                return Attribute.FORCE;
            case 3:
                return Attribute.DEXTERITE;
            case 4:
                return Attribute.INITIATIVE;
            case 5:
                return Attribute.CONSTITUTION;
        }
        return null;
    }

//    public void allocatePoints() {
//        int p=0;
//        ArrayList<Integer> points = new ArrayList<Integer>();
//        this.students=new ArrayList<Student>();
//        for (int i = 0; i < NUM_OF_STU; i++) {
//            Scanner s = new Scanner(System.in);
//            System.out.println("Please enter the resistance(from 0 to 10): \n");
//            //points.get(Attribute.RESISTANCE.ordinal()) = s.nextInt();
//            int n1 = s.nextInt();
//            while (n1 < 0 || n1 >= 11) {
//                    System.out.println("Error! Please enter the resistance(from 0 to 10): \n");
//                    n1 = s.nextInt();
//            }
//            p+=n1;
//            while (p>400) {
//                p -= n1;
//                System.out.println("The total point must be under 400! Please redistribute points");
//
//            }
//            points.add(n1);
//
//            System.out.println("Please enter the force(from 0 to 10): \n");
//            int n2 = s.nextInt();
//            while (n2 < 0 || n2 >= 11) {
//                System.out.println("Error! Please enter the force(from 0 to 10): \n");
//                n2 = s.nextInt();
//            }
//            p+=n2;
//            while (p>400) {
//                p -= n2;
//                System.out.println("The total point must be under 400! Please redistribute points");
//            }
//            points.add(n2);
//
//            System.out.println("Please enter the dexterite(from 0 to 10): \n");
//            int n3 = s.nextInt();
//            while (n3 < 0 || n3 >= 11) {
//                System.out.println("Error! Please enter the dexterite(from 0 to 10): \n");
//                n3 = s.nextInt();
//            }
//            p+=n3;
//            while (p>400) {
//                p -= n3;
//                System.out.println("The total point must be under 400! Please redistribute points");
//            }
//            points.add(n3);
//
//            System.out.println("Please enter the constitution(from 0 to 10): \n");
//            int n4 = s.nextInt();
//            while (n4 < 0 || n4 >= 11) {
//                System.out.println("Error! Please enter the constitution(from 0 to 10): \n");
//                n4 = s.nextInt();
//            }
//            p+=n4;
//            while (p>400) {
//                p -= n4;
//                System.out.println("The total point must be under 400! Please redistribute points");
//            }
//            points.add(n4);
//
//            System.out.println("Please enter the initiative(from 0 to 10): \n");
//            int n5 = s.nextInt();
//            while (n5 < 0 || n5 >= 11) {
//                System.out.println("Error! Please enter the initiative(from 0 to 10): \n");
//                n5 = s.nextInt();
//            }
//            p+=n5;
//            while (p>400) {
//                p -= n5;
//                System.out.println("The total point must be under 400! Please redistribute points");
//            }
//            points.add(n5);
//
//            System.out.println("Please enter the initiative(from 0 to 10): \n");
//            int n6 = s.nextInt();
//            while (n6 < 0 || n6 >= 11) {
//                System.out.println("Error! Please enter the initiative(from 0 to 10): \n");
//                n6 = s.nextInt();
//            }
//            p+=n6;
//            while (p>400) {
//                p -= n6;
//                System.out.println("The total point must be under 400! Please redistribute points");
//            }
//            points.add(n6);
//
//
//            this.students.add(new Student(points));
//
//            points.clear();
//
//
//        }
//
//
//
//    }

    public void allocatePoints(){
        ArrayList<Integer> points = new ArrayList<Integer>(Attribute.SIZE_OF_ATTRIBUTE.ordinal());
        int p = 0;

        for (int i = 0; i < NUM_OF_STU; i++) {
            for (int j = 0; j < Attribute.SIZE_OF_ATTRIBUTE.ordinal(); j++) {
                Scanner s = new Scanner(System.in);
                System.out.println("Please enter the " + integer2Attribute(j) + " (CATALOGY: from 0 to 2; Others: from 0 to 10): \n");
                int n = s.nextInt();
                while (n < 0 || n > MaxPoint) {
                    System.out.println("Error! Please enter the " + integer2Attribute(j) + " (CATALOGY: from 0 to 2; Others: from 0 to 10): \n");
                    n = s.nextInt();
                }
                p+=n;
                while (p > Max) {
                    p -= n;
                    System.out.println("The total point must be under " + Max +" ! Please redistribute points!");
                    n = s.nextInt();
                    p += n;
                }
                points.add(j,n);
            }

            this.students.add(new Student(points));

            //打印arraylist<points>的方法
            ArrayList<Integer> arraylist = new ArrayList<>(points);
            System.out.println("The attributes of a student are: ");
            for(int k=0 ; k<points.size();k++){
                System.out.print(points.get(k) + " ");
            }
            System.out.print("\n");

            points.clear();
        }
    }





    public void selectReserve(ArrayList<Student> students) {

        if (students.size()/6 == NUM_OF_STU) {
            students.toString();
            for (int i = 0; i < NUM_OF_RES ; i++) {
                Scanner s = new Scanner(System.in);
                System.out.println("Please decide which student is rest:");
                int n = s.nextInt();
                this.students.get(n).setReserviste(true);
            }
        } else {
            //error
        }
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

    public static void main(String args[]) {
        System.out.println("____");

        Player A = new Player();
        A.allocatePoints();
        //A.selectReserve(this.students);

    }
}

