package classes.model.characters.fighters.students;

import classes.model.characters.fighters.strategies.Attack;
import classes.model.characters.fighters.strategies.Strategy;

public class Student implements Fighter{
	protected String firstname;
	protected String lastName;
	protected int credits;
	protected int dexterity;
	protected int strength;
	protected int resistance;
	protected int constitution;
	protected int initiative;
	protected Strategy strategy;
	protected Character character;

	public enum Character {
		Student, Elite, Gobi
	}

	private Student(String firstname, String lastName) {
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

	public static Student createStudent(Character character, String firstname, String lastName) {
		Student student = new Student(firstname, lastName);
		student.character = character;
		student.firstname = firstname;
		student.lastName = lastName;
		student.credits = 30;
		student.strategy = new Attack();
		switch (character) {
		case Student:
			student.dexterity = 0;
			student.strength = 0;
			student.resistance = 0;
			student.constitution = 0;
			student.initiative = 0;
			break;
		case Elite:
			student.dexterity = 1;
			student.strength = 1;
			student.resistance = 1;
			student.constitution = 5;
			student.initiative = 1;
			break;
		case Gobi:
			student.dexterity = 2;
			student.strength = 2;
			student.resistance = 2;
			student.constitution = 10;
			student.initiative = 2;
			break;
		default:
			break;
		}
		return student;
	}
	
    public int fight(Student fighter) {
        return this.strategy.fight(this, fighter);
    }

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
	 * Get character.
	 *
	 * @return the fighter's character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * Set the fighter's credits.
	 *
	 * @param credits Should be in range [0;30]
	 */
	public void setCredits(int credits) {
		if (0 <= credits && credits <= 30 + this.getConstitution()) {
			this.credits = credits;
		}
	}

	/**
	 * Set the fighter's dexterity.
	 *
	 * @param dexterity Should be in range [0;10]
	 */
	public void setDexterity(int dexterity) {
		if (0 <= dexterity && dexterity <= 10) {
			this.dexterity = dexterity;
		}
	}

	/**
	 * Set the fighter's strength.
	 *
	 * @param strength Should be in range [0;10]
	 */
	public void setStrength(int strength) {
		if (0 <= strength && strength <= 10) {
			this.strength = strength;
		}
	}

	/**
	 * Set the fighter's resistance.
	 *
	 * @param resistance Should be in range [0;10]
	 */
	public void setResistance(int resistance) {
		if (0 <= resistance && resistance <= 10) {
			this.resistance = resistance;
		}
	}

	/**
	 * Set the fighter's constitution.
	 *
	 * @param constitution Should be in range [0;30]
	 */
	public void setConstitution(int constitution) {
		if (0 <= constitution && constitution <= 30) {
			this.constitution = constitution;
		}
	}

	/**
	 * Set the fighter's initiative.
	 *
	 * @param initiative Should be in range [0;10]
	 */
	public void setInitiative(int initiative) {
		if (0 <= initiative && initiative <= 10) {
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

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return this.firstname + " " + this.lastName;
	}
}
