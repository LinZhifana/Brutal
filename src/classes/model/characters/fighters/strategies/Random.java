package classes.model.characters.fighters.strategies;

import classes.model.characters.fighters.students.Student;

/**
 * Represents the random strategy.
 *
 * @version 1.0
 */
public class Random implements Strategy {
    private Strategy strategy;

    /**
     * Create a Random instance.
     */
    public Random() {
    }

    /**
     * Make a fighter fight another one.
     *
     * @see Student
     * @param f1 fighter 1
     * @param f2 fighter 2
     */
    public int fight(Student f1, Student f2) {
        this.setStrategy();
        return this.strategy.fight(f1, f2);
    }

    /**
     * Set a random Strategy between Defense and Attack.
     */
    private void setStrategy() {
        java.util.Random random = new java.util.Random();
        Strategy[] strategies = {new Defense(), new Attack()};
        this.strategy = strategies[random.nextInt(strategies.length)];
    }

    /**
     * Get strategy.
     *
     * @return the strategy
     */
    public Strategy getStrategy() {
        return this.strategy;
    }

    @Override
    public String toString() {
        return "Random";
    }

	@Override
	public int fight(classes.model.characters.fighters.studentsSep.Student f1,
			classes.model.characters.fighters.studentsSep.Student f2) {
		this.setStrategy();
        return this.strategy.fight(f1, f2);
	}

	
}
