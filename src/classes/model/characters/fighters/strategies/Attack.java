package classes.model.characters.fighters.strategies;

import classes.model.characters.fighters.studentsSep.Student;

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
	public int fight(Student f1, Student f2) {
		return this.attack(f1, f2);
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
	 * Compute the result of the fight according to the method defined in the
	 * subject.
	 *
	 * @param attacker A fighter
	 * @param attacked A fighter
	 */
	private int attack(Student attacker, Student attacked) {
		java.util.Random random = new java.util.Random();
		int damageAmmout = 0;
		int randn = random.nextInt(101);
		if (randn <= 40 + 3 * attacker.getDexterity()) {
			double attackCoef = random.nextDouble();
			double damageCoef = Math.max(0, Math.min(100, 10 * attacker.getStrength() - 5 * attacked.getResistance()))
					/ 100;
			int refDamage = 10;
			damageAmmout = (int) (attackCoef * (1 + damageCoef) * refDamage);
			attacked.setCredits(attacked.getCredits() - damageAmmout);
		}
		return -damageAmmout;
	}

	@Override
	public String toString() {
		return "Attack";
	}

	@Override
	public int fight(classes.model.characters.fighters.students.Student attacker,
			classes.model.characters.fighters.students.Student attacked) {
		java.util.Random random = new java.util.Random();
		int damageAmmout = 0;
		int randn = random.nextInt(101);
		if (randn <= 40 + 3 * attacker.getDexterity()) {
			double attackCoef = random.nextDouble();
			double damageCoef = Math.max(0, Math.min(100, 10 * attacker.getStrength() - 5 * attacked.getResistance()))
					/ 100;
			int refDamage = 10;
			damageAmmout = (int) (attackCoef * (1 + damageCoef) * refDamage);
			attacked.setCredits(attacked.getCredits() - damageAmmout);
		}
		return -damageAmmout;
		
	}
}
