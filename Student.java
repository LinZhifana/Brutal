package student;

import java.util.ArrayList;

public class Student implements Comparable<Student> {
    private static final int[][] INITIAL_VALUES = {{0, 0}, {1, 5}, {2, 10}};

    private Category category;
    private boolean isReservist = false;
    private int creditsECTS = 30;
    private int resistance = 0;     // 防御 0-10
    private int force = 0;          // 力量 0-10
    private int dexterite = 0;      // 敏捷 0-10
    private int constitution = 0;   // 生命值上限 0-10
    private int initiative = 0;     // 主动性（决定行动顺序） 0-10
    private Strategy strategy;
    private int next = (int) Math.random();

    public Student(ArrayList<Integer> points) {
        if (points.size() == Attribute.SIZE_OF_ATTRIBUTE.ordinal()) {
            this.category = Category.values()[points.get(Attribute.CATEGORY.ordinal()).intValue()];
            this.resistance = points.get(Attribute.RESISTANCE.ordinal()) + INITIAL_VALUES[this.category.ordinal()][0];
            this.force = points.get(Attribute.FORCE.ordinal()) + INITIAL_VALUES[this.category.ordinal()][0];
            this.dexterite = points.get(Attribute.DEXTERITE.ordinal()) + INITIAL_VALUES[this.category.ordinal()][0];
            this.constitution = points.get(Attribute.CONSTITUTION.ordinal()) + INITIAL_VALUES[this.category.ordinal()][1];
            this.initiative = points.get(Attribute.INITIATIVE.ordinal()) + INITIAL_VALUES[this.category.ordinal()][0];
            this.creditsECTS = 30 + this.constitution;
        } else {
            System.out.println("ArrayList<Integer> points.length is not 6");
        }
    }

    private int cure(Student student) {
        int x = (int) (Math.random() * 101), z = 0;
        if (x <= 20 + 6 * this.dexterite) {
            double y = ((Math.random() * 610) + 1) / 1000;
            z = (int) Math.floor(y * (student.constitution + 10));
        }
        return z < student.constitution + 30 ? z : student.constitution + 30;
    }

    private int attack(Student student) {
        int x = (int) (Math.random() * 101), z = 0;
        double coefficient = 0;
        double reference = 0.5; // 默认10
        if (x <= 40 + 3 * this.dexterite) {
            double y = ((Math.random() * 1000) + 1) / 1000;
            coefficient = Math.max(0, Math.min(100, 10 * this.force - 5 * student.resistance));
            z = (int) Math.floor(y * (1 + coefficient) * reference);
        }
        return z;
    }

    public int compareTo(Student s) {
        return this.initiative - s.initiative;
    }

    public boolean isDead() {
        return this.creditsECTS <= 0;
    }

    public int act(Student student) {
        int ret = 0;
        if (student != null) {
            switch (this.strategy) {
                case OFFENSIVE:
                    ret = attack(student);
                    break;
                case DEFENSIVE:
                    ret = cure(student);
                    break;
                case RANDOM:
                    ret = this.next != 0 ? attack(student) : cure(student);
                    this.next ^= 1;
                    break;
            }
        }
        return ret;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isReservist() {
        return isReservist;
    }

    public int getCreditsECTS() {
        return creditsECTS;
    }

    public int getResistance() {
        return resistance;
    }

    public int getForce() {
        return force;
    }

    public int getDexterite() {
        return dexterite;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getInitiative() {
        return initiative;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setCreditsECTS(int creditsECTS) {
        this.creditsECTS = creditsECTS;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setReservist(boolean reservist) {
        this.isReservist = reservist;
    }

    public static void main(String args[]) {
    }
}
