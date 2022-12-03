package game;

import game.Category;
import student.Programme;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static final int NUM_OF_STU = 3;
    private static final int NUM_OF_RES = 2;
    private static final int MAX_POINT = 10;
    private static final int TOTAL_POINT = 10;
    private static final int[] NUM_OF_EACH_STU_KIND = {1, 1, 1};

    private Programme programme;
    private Players player;
    private ArrayList<Student> students = new ArrayList<Student>(NUM_OF_STU);
    private ArrayList<ZoneName> zoneNames;

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

    //小于400
    private ArrayList<ArrayList<Integer>> allocatePoints() {
        int length = Attribute.values().length - 1;
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
            for (int k : points) {
                ps.add(k);
            }
            ret.add(ps);
        }
        return ret;
    }

    private boolean findDupicateInArray(String[] a) {
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

    public Player(Players player) {
        this.player = player;
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

    public void distributeStudent(Zone zone) {
        System.out.println(this.toString());
        //分配打印
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
            if (findDupicateInArray(userPointsStr)) {
                System.out.println("有重复元素");
                isEnterWrong = true;
                continue;
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

        Student[] sArr = new Student[userPointsStr.length];
        for (int i = 0; i < userPointsStr.length; i++) {
            sArr[i] = this.students.get(Integer.parseInt(userPointsStr[i]) - 1);
        }
        for (Student s : sArr) {
            zone.addStudent(s, this.player);
            delStudent(s);
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

            if (findDupicateInArray(userPointsStr)) {
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

    boolean isWin() {
        return this.zoneNames.size() >= 3 ? true : false;
    }


    public String toString() {
        StringBuffer str = new StringBuffer("PLAYER :" + this.player.name() + "\n");
        int i = 0;
        for (Student s : this.students) {
            str.append((++i) + " : " + s.toString() + "\n");
        }
        return str.toString();
    }

    public static void main(String args[]) {
        Player p = new Player(Players.A);
        Zone z = new Zone(ZoneName.HI);
        p.setReservist();
        p.distributeStudent(z);
        System.out.println(z.toString());
    }
}
