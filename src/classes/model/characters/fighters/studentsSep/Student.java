package classes.model.characters.fighters.studentsSep;

import classes.model.characters.fighters.strategies.Attack;
import classes.model.characters.fighters.strategies.Strategy;

/**
 * Represents a Student.
 *
 * @version 1.0
 */
public class Student implements Fighter {
    protected String firstname;
    protected String lastName;
    protected int credits;
    protected int dexterity;
    protected int strength;
    protected int resistance;
    protected int constitution;
    protected int initiative;
    protected Strategy strategy;

    /**
     * Create a student with the specified name and the default characteristics.
     *
     * @see Attack
     * @param firstname the student's firstname
     * @param lastName the student's lastname
     */
    public Student(
            String firstname,
            String lastName
    ) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.credits = 30;
        this.dexterity = 0;
        this.strength = 0;
        this.resistance = 0;
        this.constitution = 0;
        this.initiative = 0;
        this.strategy = new Attack();
    }

    /**
     * Create a gobi master with the specified name and the specified characteristics.
     *
     * @see Attack
     * @param firstname the student's firstname
     * @param lastName the student's lastname
     * @param dexterity the student's dexterity
     * @param strength the student's strength
     * @param resistance the student's resistance
     * @param constitution the student's constitution
     * @param initiative the student's initiative
     */
    public Student(
            String firstname,
            String lastName,
            int dexterity,
            int strength,
            int resistance,
            int constitution,
            int initiative
    ) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.dexterity = dexterity;
        this.strength = strength;
        this.resistance = resistance;
        this.constitution = constitution;
        this.initiative = initiative;
        this.credits = 30;
        this.strategy = new Attack();
    }

    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
     * Fight another fighter.
     *
     * @param fighter The fighter to combat
     */
    public void fight(Student fighter) {
        this.strategy.fight(this, fighter);
    }

    /**
     * Get credits.
     *
     * @return the fighter's credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Get dexterity.
     *
     * @return the fighter's dexterity
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * Get strength.
     *
     * @return the fighter's strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Get resistance.
     *
     * @return the fighter's resistance
     */
    public int getResistance() {
        return resistance;
    }

    /**
     * Get constitution.
     *
     * @return the fighter's constitution
     */
    public int getConstitution() {
        return constitution;
    }

    /**
     * Get initiative.
     *
     * @return the fighter's initiative
     */
    public int getInitiative() {
        return initiative;
    }

    /**
     * Get strategy.
     *
     * @return the fighter's strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Set the fighter's credits.
     *
     * @param credits Should be in range [0;30]
     */
    public void setCredits(int credits) {
        if(0 <= credits && credits <= 30 + this.getConstitution()) {
            this.credits = credits;
        }
    }

    /**
     * Set the fighter's dexterity.
     *
     * @param dexterity Should be in range [0;10]
     */
    public void setDexterity(int dexterity) {
        if(0 <= dexterity && dexterity <= 10) {
            this.dexterity = dexterity;
        }
    }

    /**
     * Set the fighter's strength.
     *
     * @param strength Should be in range [0;10]
     */
    public void setStrength(int strength) {
        if(0 <= strength && strength <= 10) {
            this.strength = strength;
        }
    }

    /**
     * Set the fighter's resistance.
     *
     * @param resistance Should be in range [0;10]
     */
    public void setResistance(int resistance) {
        if(0 <= resistance && resistance <= 10) {
            this.resistance = resistance;
        }
    }

    /**
     * Set the fighter's constitution.
     *
     * @param constitution Should be in range [0;30]
     */
    public void setConstitution(int constitution) {
        if(0 <= constitution && constitution <= 30) {
            this.constitution = constitution;
        }
    }

    /**
     * Set the fighter's initiative.
     *
     * @param initiative Should be in range [0;10]
     */
    public void setInitiative(int initiative) {
        if(0 <= initiative && initiative <= 10) {
            this.initiative = initiative;
        }
    }

    /**
     * Set the fighter's strategy.
     *
     * @param strategy Should be one of [Attack, Defense, Random]
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastName;
    }
}
