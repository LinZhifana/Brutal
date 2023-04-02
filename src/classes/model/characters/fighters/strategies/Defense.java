package classes.model.characters.fighters.strategies;

import classes.model.characters.fighters.students.Student;

/**
 * Represents the defense strategy.
 *
 * @version 1.0
 */
public class Defense implements Strategy {
    /**
     * Create a Defense instance.
     */
    public Defense() {
    }

    /**
     * Make a fighter fight another one.
     *
     * @see Student
     * @param f1 The healer
     * @param f2 The fighter to heal
     */
    public void fight(Student f1, Student f2) {
        this.heal(f1, f2);
    }

    /**
     * Get strategy.
     *
     * @return the strategy
     */
    @Override
    public Strategy getStrategy() {
        return this;
    }

    /**
     * Compute the result of the fight according to the method defined in the subject.
     *
     * @param healer A fighter
     * @param fighterToHeal A fighter
     */
    private void heal(Student healer, Student fighterToHeal) {
        java.util.Random random = new java.util.Random();
        int randn = random.nextInt(101);
        if(0 <= randn && randn <= 20 + 6*healer.getDexterity()) {
            double healingCoef = random.nextDouble() * 0.6;
            int healingAmount = (int) (healingCoef * (10 + fighterToHeal.getConstitution()));
            int totalHeal = Math.min(fighterToHeal.getCredits() + healingAmount, fighterToHeal.getCredits() + fighterToHeal.getConstitution());
            fighterToHeal.setCredits(totalHeal);
        }
    }

    @Override
    public String toString() {
        return "Defense";
    }
}
