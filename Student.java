package student;

import java.util.ArrayList;
import java.util.Scanner;

public class Student implements Comparable<Student>, takeStrategy {
    private Category category;
    private boolean isReserviste = false;
    private int creditsECTS = 30;
    private int resistance;     // 防御 0-10
    private int force;          // 力量 0-10
    private int dexterite = 0;  // 敏捷 0-10 1分:3%闪避,医疗6%成功率
    private int constitution;   // 生命值上限 0-10
    private int initiative;     // 主动性（决定行动顺序） 0-10
    private Strategy strategy;

    private int next = (int) Math.random();

    private Category integer2Category(Integer i) {
        switch (i.intValue()) {
            case 0:
                return Category.ETUDIANT;
            case 1:
                return Category.ETUDIANT_ELITE;
            case 2:
                return Category.MAITRE_GOBI;
        }
        return null;
    }

    public Student(ArrayList<Integer> points) {
        int[][] init = {{0, 0}, {1, 5}, {2, 10}};
        if (points.size() == Attribute.SIZE_OF_ATTRIBUTE.ordinal()) {
            this.category = integer2Category(points.get(Attribute.CATEGORY.ordinal()));
            this.resistance = points.get(Attribute.RESISTANCE.ordinal()) + init[this.category.ordinal()][0];
            this.force = points.get(Attribute.FORCE.ordinal()) + init[this.category.ordinal()][0];
            this.dexterite = points.get(Attribute.DEXTERITE.ordinal()) + init[this.category.ordinal()][0];
            this.constitution = points.get(Attribute.CONSTITUTION.ordinal()) + init[this.category.ordinal()][1];
            this.initiative = points.get(Attribute.INITIATIVE.ordinal()) + init[this.category.ordinal()][0];
            this.creditsECTS = 30 + this.constitution;
        }
    }

    public int cure(Student student) {
        int x = (int) (Math.random() * 101), z = 0;
        // 𝑥 ∈ [0 , 20 + 6 ∗ 𝑑𝑒𝑥𝑡é𝑟𝑖𝑡é 𝑑𝑢 𝑠𝑜𝑖𝑔𝑛𝑎𝑛𝑡] 治疗者
        if (x <= 20 + 6 * this.dexterite) {
            double y = ((Math.random() * 610) + 1) / 1000;
            // 𝐸(𝑦 ∗ (10 + 𝑐𝑜𝑛𝑠𝑖𝑡𝑢𝑡𝑖𝑜𝑛 𝑑𝑢 𝑠𝑜𝑖𝑔𝑛é)) crédits ECTS avec 𝑦 ∈]0, 0.6] 被治疗者
            z = (int) Math.floor(y * (student.constitution + 10));
        }
        // 值小于生命上限
        return z < student.constitution + 30 ? z : student.constitution + 30;
    }

    public int attack(Student student) {
        int x = (int) (Math.random() * 101), z = 0;
        double coef = 0;
        double ref = 0.5; // 默认10
        //𝑥∈ [0 , 40 + 3 ∗ 𝑑𝑒𝑥𝑡é𝑟𝑖𝑡é 𝑑 𝑙′𝑎𝑡𝑎𝑞𝑢𝑛𝑡]
        if (x <= 40 + 3 * this.dexterite) {
            double y = ((Math.random() * 1000) + 1) / 1000;
            //max(0, min(100, 10 ∗ 𝑓𝑜𝑟𝑐𝑒 𝑑𝑒 𝑙′𝑎𝑡𝑡𝑎𝑞𝑢𝑎𝑛𝑡 − 5 ∗ 𝑟é𝑠𝑖𝑠𝑡𝑎𝑛𝑐𝑒 𝑑𝑒 𝑙′𝑎𝑡𝑡𝑎𝑞𝑢é))
            coef = Math.max(0, Math.min(100, 10 * this.force - 5 * student.resistance));
            //𝐸(𝑦 ∗ (1 + 𝑐𝑜𝑒𝑓𝑓𝑖𝑐𝑖𝑒𝑛𝑡 𝑑é𝑔â𝑡) ∗ 𝑑é𝑔â𝑡 𝑑𝑒 𝑟é𝑓é𝑟𝑒𝑛𝑐𝑒)
            z = (int) Math.floor(y * (1 + coef) * ref);
        }
        return z;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setReserviste(boolean reserviste) {
        this.isReserviste = reserviste;
    }

    public int getCreditsECTS() {
        return this.creditsECTS;
    }

    public int compareTo(Student s) {
        return this.initiative - s.initiative;
    }

    public int act(Student student) {
        int ret = 0;
        if(student != null){
            switch (this.strategy.ordinal()) {
                case 0:
                    ret = attack(student);
                    break;
                case 1:
                    ret = cure(student);
                    break;
                case 2:
                    if (next != 0) {
                        this.next = 0;
                        ret = attack(student);
                    } else {
                        this.next = 1;
                        ret = cure(student);
                    }
                    ;
                    break;
            }
        }
        return ret;
    }

    public boolean isDead() {
        return this.creditsECTS <= 0;
    }


    public static void main(String args[]) {
        System.out.println("____");
        Scanner s = new Scanner(System.in);
        String str = s.next();
        System.out.println(str);
    }
}
