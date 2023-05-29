package classes.model.game;

import classes.model.battlefields.District;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.fighters.students.Student.Character;
import classes.model.characters.players.Player;
import classes.model.characters.players.Player.Branch;
import classes.model.characters.players.Player.Team;
import classes.model.fight.FightingStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A class representing the game "C'est du brutal". How to use it:
 * 
 * <pre>
 * Game game = new Game(new TurnBased());
 * game.play();
 * </pre>
 *
 * @version 1.0
 */
public class Game {
	static final int nStudent = 15;
	static final int nElite = 4;
	static final int nMaster = 1;
	static final String[] firstNames = { "Laura", "Miki", "Harry", "James", "Gregory", "Emily", "Curtis", "Damian",
			"Steven", "Daniel", "John", "Sara", "Mary", "Christina", "Mario", "Lola", "Katharine", "Cynthia", "Brenda",
			"Timothy" };
	static final String[] lastNames = { "Barrett", "Ryan", "Matthews", "Sparks", "Cordoba", "Reynolds", "Zieman",
			"Johnson", "Capdeville", "Srour", "Lundberg", "Ryan", "Amidon", "Klumpp", "Roper", "Piatkowski", "Aguilar",
			"Gray", "Woods", "Swisher" };
	static final String[] districtNames = { "Library", "Student office", "Administrative district", "Industrial halls",
			"Sports hall" };
	private Player p1;
	private Player p2;
	private final District[] districts;
	private final FightingStyle fightingStyle;

	/**
	 * Creates a Game with the specified fighting style
	 *
	 * @param fightingStyle The fighting style that game will use.
	 * @see #generateDistricts()
	 * @see #initDefaultPlayers()
	 */
	public Game(FightingStyle fightingStyle) {
		this.fightingStyle = fightingStyle;
		this.districts = this.generateDistricts();
		this.initDefaultPlayers();
	}

	/**
	 * Initialize both players with default parameters.
	 *
	 * @see Player
	 */
	private void initDefaultPlayers() {
		this.p1 = new Player("Player 1", this.generateTeam(), Branch.ISI, Team.TEAM1);
		this.p2 = new Player("Player 2", this.generateTeam(), Branch.ISI, Team.TEAM2);
	}

	/**
	 * Generate an array of districts, initialized with the names defined in the
	 * class.
	 *
	 * @see District
	 * @return an array of districts
	 */
	private District[] generateDistricts() {
		District[] districts = new District[Game.districtNames.length];

		for (int i = 0; i < Game.districtNames.length; i++) {
			districts[i] = new District(Game.districtNames[i]);
		}
		return districts;
	}

	/**
	 * Generate an ArrayList of Students with random names.
	 *
	 * @see Student
	 * @see EliteStudent
	 * @see GobiMaster
	 * @return an ArrayList of Students
	 */
	private ArrayList<Student> generateTeam() {
		Random rand = new Random();
		ArrayList<Student> f = new ArrayList<Student>();
		for (int i = 0; i < nStudent; i++) {
			f.add(Student.createStudent(Character.Student, firstNames[rand.nextInt(firstNames.length)],
					lastNames[rand.nextInt(lastNames.length)]));
		}

		for (int i = 0; i < nElite; i++) {
			f.add(Student.createStudent(Character.Elite, firstNames[rand.nextInt(firstNames.length)],
					lastNames[rand.nextInt(lastNames.length)]));
		}

		for (int i = 0; i < nMaster; i++) {
			f.add(Student.createStudent(Character.Gobi, firstNames[rand.nextInt(firstNames.length)],
					lastNames[rand.nextInt(lastNames.length)]));
		}

		return f;
	}

	/**
	 * Check if the game is over (if 1 player controls 3 or more districts), if so,
	 * the winner and the score is displayed.
	 *
	 * @return a boolean indicating if the game is over or not
	 */
	public boolean checkWin() {
		int scorePlayer1 = 0;
		int scorePlayer2 = 0;
		for (District d : this.districts) {
			if (d.getTeam(this.p2.getTeam()).isEmpty()) {
				scorePlayer1++;
			} else if (d.getTeam(this.p1.getTeam()).isEmpty()) {
				scorePlayer2++;
			}
			if (scorePlayer1 > 2) {
				return true;
			} else if (scorePlayer2 > 2) {
				return true;
			}
		}
		return false;
	}

	public int getScoreP1() {
		int score = 0;
		for (District d : this.districts) {
			if (d.getTeam(this.p2.getTeam()).isEmpty() && !d.getTeam(this.p1.getTeam()).isEmpty())
				score++;
		}
		return score;
	}

	public int getScoreP2() {
		int score = 0;
		for (District d : this.districts) {
			if (d.getTeam(this.p1.getTeam()).isEmpty() && !d.getTeam(this.p2.getTeam()).isEmpty())
				score++;
		}
		return score;
	}

	/**
	 * Get the active districts.
	 *
	 * @see District#isFightOver()
	 * @return an array of districts where the fights aren't over.
	 */
	public District[] getActiveDistricts() {
		ArrayList<District> temp = new ArrayList<>();
		for (District d : this.districts) {
			if (d.isFightOver() == Team.NONE)
				temp.add(d);
		}
		District[] activeDistricts = new District[temp.size()];
		activeDistricts = temp.toArray(activeDistricts);
		return activeDistricts;
	}

	public District[] getDistricts() {
		return districts;
	}

	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public FightingStyle getFightingStyle() {
		return fightingStyle;
	}

	@Override
	public String toString() {
		return "Game{" + "firstNames=" + Arrays.toString(firstNames) + ", lastNames=" + Arrays.toString(lastNames)
				+ ", districtNames=" + Arrays.toString(districtNames) + ", p1=" + p1 + ", p2=" + p2 + ", districts="
				+ Arrays.toString(districts) + ", fightingStyle=" + fightingStyle + '}';
	}
}
