package classes.model.battlefields;

import classes.model.characters.fighters.students.Student;
import classes.model.characters.players.Team;

import java.util.ArrayList;

/**
 * Represents a district.
 *
 * @version 1.0
 */
public class District {
    private final String name;
    private final ArrayList<Student> team1;
    private final ArrayList<Student> team2;

    /**
     * Creates a district with the specified name.
     *
     * @param name The district's name
     */
    public District(String name) {
        this.name = name;
        this.team1 = new ArrayList<>();
        this.team2 = new ArrayList<>();
    }

    /**
     * Add a fighter to the specified team.
     *
     * @param f The fighter
     * @param team The team in which the fighter should be added
     */
    public void addFighterTeam(Student f, Team team) {
        if (team == Team.TEAM1) {
            this.team1.add(f);
        } else if (team == Team.TEAM2) {
            this.team2.add(f);
        }
    }

    /**
     * Remove a fighter of the specified team.
     *
     * @param f The fighter
     * @param team The team in which the fighter should be removed
     */
    public void removeFighterTeam(Student f, Team team) {
        if (team == Team.TEAM1) {
            this.team1.remove(f);
        } else if (team == Team.TEAM2) {
            this.team2.remove(f);
        }
    }

    /**
     * Check if the fight on the district is over.
     *
     * @return None or the team that controls the district
     */
    public Team isFightOver() {
        if (this.team1.isEmpty()) {
            return Team.TEAM2;
        } else if (this.team2.isEmpty()) {
            return Team.TEAM1;
        }
        return Team.NONE;
    }

    /**
     * Compute the total amount of credits of a team.
     *
     * @param team The team for which the total should be computed
     * @return The total amount of credits of the specified team
     */
    public int getTotalCreditsTeam(Team team) {
        if (team == Team.TEAM1) {
            return this.team1.stream().mapToInt(Student::getCredits).sum();
        } else if (team == Team.TEAM2) {
            return this.team2.stream().mapToInt(Student::getCredits).sum();
        }
        return -1;
    }

    /**
     * Get name.
     *
     * @return The district's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get team.
     *
     * @param team The team to get
     * @return The fighters of the specified team
     */
    public ArrayList<Student> getTeam(Team team) {
        if (team == Team.TEAM1) {
            return this.team1;
        } else if (team == Team.TEAM2) {
            return this.team2;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
