package game;

public class Student implements Config, Comparable<Student> {
    private int NEXT_ACT = (int) Math.round(Math.random());
    private String name;

    private Category category;
    private Strategy strategy;
    private ZoneName zoneName;
    private boolean isReservist = false;
    private int resistance = 0;
    private int force = 0;
    private int dexterite = 0;
    private int initiative = 0;
    private int constitution = 0;
    private int creditsECTS = 0;

    public Student(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public ZoneName getZoneName() {
        return zoneName;
    }

    public void setZoneName(ZoneName zoneName) {
        this.zoneName = zoneName;
    }

    public boolean isReservist() {
        return isReservist;
    }

    public void setReservist(boolean reservist) {
        isReservist = reservist;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance + INITIAL_VALUES[getCategory().ordinal()][0];
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force + INITIAL_VALUES[getCategory().ordinal()][0];
    }

    public int getDexterite() {
        return dexterite;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite + INITIAL_VALUES[getCategory().ordinal()][0];
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative + INITIAL_VALUES[getCategory().ordinal()][0];
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution + INITIAL_VALUES[getCategory().ordinal()][1];
        this.creditsECTS = constitution + 30;
    }

    public int getCreditsECTS() {
        return creditsECTS;
    }

    public void setCreditsECTS(int creditsECTS) {
        this.creditsECTS =
                creditsECTS > getConstitution() + 30 ? getConstitution() + 30 : creditsECTS;
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

    public boolean isDead() {
        return getCreditsECTS() <= 0;
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
                    ret = this.NEXT_ACT != 0 ? attack(student) : cure(student);
                    this.NEXT_ACT ^= 1;
                    break;
            }
        }
        return ret;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("-------- " + this.name + " --------\n");
        result.append(" CATEGORY : " + this.category);
        result.append(" STRATEGY : " + this.strategy + "\n");
        result.append(" RESISTANCE : " + this.resistance);
        result.append(" FORCE : " + this.force);
        result.append(" DEXTERITE : " + this.dexterite + "\n");
        result.append(" INITIATIVE : " + this.initiative);
        result.append(" CONSTITUTION : " + this.constitution);
        result.append(" CREDITS_ECTS : " + this.creditsECTS + "\n");
        result.append(" IS RESERVIST " + this.isReservist);
        return result.toString();
    }

    public int compareTo(Student s) {
        return s.getInitiative() - this.getInitiative();
    }

    public static void main(String[] args) {
        System.out.println("hello");
        Student student = new Student("jj");
        System.out.println(student);
    }
}
