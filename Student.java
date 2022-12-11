package game;

import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Comparable<Student> {
    private static final int[][] INITIAL_VALUES = {{0, 0}, {1, 5}, {2, 10}};
    private int attributeLength = Attribute.values().length;
    private int[] attributes = new int[attributeLength];
    private boolean isReservist = false;
    private int nextAct = (int) Math.round(Math.random());

    public Student(ArrayList<Integer> points) {
        if (points.size() == this.attributeLength - 1) {
            for (int i = 0; i < this.attributeLength - 1; i++) {
                Attribute a = Attribute.values()[i];
                if (a == Attribute.CATEGORY || a == Attribute.STRATEGY)
                    this.attributes[a.ordinal()] = points.get(a.ordinal()).intValue();
                else
                    this.attributes[a.ordinal()] = points.get(a.ordinal()).intValue() + INITIAL_VALUES[getCategory().ordinal()][0];
            }
            this.attributes[Attribute.CONSTITUTION.ordinal()] = points.get(Attribute.CONSTITUTION.ordinal()).intValue() + INITIAL_VALUES[getCategory().ordinal()][1];
            this.attributes[Attribute.CREDITS_ECTS.ordinal()] = getConstitution() + 30;
        } else {
            System.out.println("输入数组长度不对");
        }
    }

    private int cure(Student student) {
        int x = (int) (Math.random() * 101), z = 0;
        if (x <= 20 + 6 * this.getDexterite()) {
            double y = ((Math.random() * 610) + 1) / 1000;
            z = (int) Math.floor(y * (getConstitution() + 10));
        }
        return z < getConstitution() + 30 ? z : getConstitution() + 30;
    }

    private int attack(Student student) {
        int x = (int) (Math.random() * 101), z = 0;
        double coefficient = 0;
        double reference = 10; // 默认10
        if (x <= 40 + 3 * getDexterite()) {
            double y = ((Math.random() * 1000) + 1) / 1000;
            coefficient = Math.max(0, Math.min(100, 10 * getForce() - 5 * getResistance())) / 100;
            z = (int) Math.floor(y * (1 + coefficient) * reference);
        }
        return -z;
    }

    public int act(Student student) {
        int ret = 0;
        if (!student.isDead()) {
            switch (getStrategy()) {
                case OFFENSIVE:
                    ret = attack(student);
                    break;
                case DEFENSIVE:
                    ret = cure(student);
                    break;
                case RANDOM:
                    ret = this.nextAct != 0 ? attack(student) : cure(student);
                    this.nextAct ^= 1;
                    break;
            }
        }
        return ret;
    }

    public boolean isDead() {
        return getCreditsECTS() <= 0;
    }

    public boolean isReservist() {
        return isReservist;
    }

    //getter
    public int getCreditsECTS() {
        return this.attributes[Attribute.CREDITS_ECTS.ordinal()];
    }

    public int getResistance() {
        return this.attributes[Attribute.RESISTANCE.ordinal()];
    }

    public int getForce() {
        return this.attributes[Attribute.FORCE.ordinal()];
    }

    public int getDexterite() {
        return this.attributes[Attribute.DEXTERITE.ordinal()];
    }

    public int getConstitution() {
        return this.attributes[Attribute.CONSTITUTION.ordinal()];
    }

    public int getInitiative() {
        return this.attributes[Attribute.INITIATIVE.ordinal()];
    }

    public Strategy getStrategy() {
        int i = this.attributes[Attribute.STRATEGY.ordinal()];
        return Strategy.values()[i];
    }

    public Category getCategory() {
        int i = this.attributes[Attribute.CATEGORY.ordinal()];
        return Category.values()[i];
    }

    public int getAttributeLength() {
        return this.attributeLength;
    }

    //setter
    public void setStrategy(Strategy s) {
        this.attributes[Attribute.STRATEGY.ordinal()] = s.ordinal();
    }

    public void setReservist() {
        this.isReservist = true;
    }

    public void setCreditsECTS(int i) {
        this.attributes[Attribute.CREDITS_ECTS.ordinal()] =
                i > 30 + getConstitution() ? 30 + getConstitution() : i;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("Student : ");
        int c = this.attributes[Attribute.values()[0].ordinal()];
        int s = this.attributes[Attribute.values()[1].ordinal()];
        result.append(Attribute.values()[0].name() + " : " + Category.values()[c].name() + " ");
        result.append(Attribute.values()[1].name() + " : " + Strategy.values()[s].name() + " ");
        for (int i = 2; i < this.attributeLength; i++) {
            result.append(Attribute.values()[i].name() + " : " + this.attributes[Attribute.values()[i].ordinal()] + " ");
        }
        result.append(" | IS RESERVIST " + this.isReservist);
        return result.toString();
    }

    public int compareTo(Student s) {
        return s.getInitiative() - this.getInitiative();
    }
}
