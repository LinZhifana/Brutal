package classes.model.characters.fighters.strategies;

import classes.model.characters.fighters.students.Student;

/**
 * Represents the attack strategy.
 *
 * @version 1.0
 */
public class Attack implements Strategy {
    /**
     * Create an Attack instance.
     */
    public Attack() {

    }

    /**
     * Make a fighter fight another one.
     *
     * @see Student
     * @param f1 The attacker
     * @param f2 The attacked
     */
    public void fight(Student f1, Student f2) {
        this.attack(f1, f2);
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
     * @param attacker A fighter
     * @param attacked A fighter
     */
    private void attack(Student attacker, Student attacked) {
        java.util.Random random = new java.util.Random();
        int randn = random.nextInt(101);
        if (randn <= 40 + 3 * attacker.getDexterity()) {
            double attackCoef = random.nextDouble();
            double damageCoef = Math.max(0, Math.min(100, 10 * attacker.getStrength() - 5 * attacked.getResistance())) / 100;
            int refDamage = 10;
            int damageAmmout = (int) (attackCoef * (1 + damageCoef) * refDamage);
            attacked.setCredits(attacked.getCredits() - damageAmmout);
        }
    }

    @Override
    public String toString() {
        return "Attack";
    }
}
