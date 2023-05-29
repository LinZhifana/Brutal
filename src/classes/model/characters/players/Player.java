package classes.model.characters.players;

//import classes.model.battlefields.District;
import classes.model.characters.fighters.students.Student;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a PLayer
 *
 * @version 1.0
 */
public class Player {
	private int points;
	private String name;
	private Branch branch;
	private final Team team;
	private final ArrayList<Student> fighters, reservists;

	public enum Branch {
		ISI, RT, A2I, GI, GM, MTE, MM
	}

	public enum Team {
		TEAM1, TEAM2, NONE
	}

	/**
	 * Create a Player with the specified name, fighters, branch and team.
	 *
	 * @param name     The player's name
	 * @param fighters An array list of fighters
	 * @param branch   The player's branch
	 * @param team     The player's team
	 */
	public Player(String name, ArrayList<Student> fighters, Branch branch, Team team) {
		this.name = name;
		this.fighters = fighters;
		this.reservists = new ArrayList<Student>();
		this.branch = branch;
		this.points = 400;
		this.team = team;
	}

	/**
	 * Compute the total amount of credits for each district.
	 *
	 * @param districts An array of districts
	 * @return the amount of credits for on each district
	 */
//    public String checkCreditsDistricts(District[] districts) {
//        StringBuilder message = new StringBuilder();
//        for(District d: districts) {
//            message.append(d.getName()).append(": ").append(d.getTotalCreditsTeam(this.team)).append("\n");
//        }
//        return message.toString();
//    }

	/**
	 * Get fighters.
	 *
	 * @return An arrayList of Student
	 */
	public ArrayList<Student> getFighters() {
		return fighters;
	}

	public int getPoints() {
		return points;
	}

	/**
	 * Get Team.
	 * 
	 * @return The player's team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Get name.
	 *
	 * @return The player's name
	 */
	public String getName() {
		return name;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public ArrayList<Student> getReservists() {
		return reservists;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Player{" + "points=" + points + ", name='" + name + '\'' + ", fighters="
				+ Arrays.toString(fighters.toArray()) + ", reservists=" + Arrays.toString(reservists.toArray())
				+ ", branch=" + branch + '}';
	}
}
