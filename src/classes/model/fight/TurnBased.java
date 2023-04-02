package classes.model.fight;

import classes.model.characters.fighters.strategies.Defense;
import classes.model.characters.fighters.strategies.Strategy;
import classes.model.characters.fighters.students.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Represents the turn based fighting style.
 *
 * @version 1.0
 */
public class TurnBased implements FightingStyle {

    /**
     * Make the teams fight each other. The fighters of each team are sorted by their initiative. Each team has a probability of 1/2 of starting the fight.
     *
     * @see #teamFight(ArrayList, ArrayList)
     * @param team1 The team of the player 1
     * @param team2 The team of the player 2
     */
    public void fight(ArrayList<Student> team1, ArrayList<Student> team2) {
        Random rand = new Random();
        team1.sort(Comparator.comparing(Student::getInitiative));
        Collections.reverse(team1);
        team2.sort(Comparator.comparing(Student::getInitiative));
        Collections.reverse(team2);

        if (rand.nextInt() < 0.5) {
            this.teamFight(team1, team2);
            this.teamFight(team2, team1);
        } else {
            this.teamFight(team2, team1);
            this.teamFight(team1, team2);
        }
    }

    /**
     * Each fighter of the team1 attacks, according to their strategy.
     *
     * @see Student#fight(Student) 
     * @see #getFighterWithMinCredit(ArrayList) 
     * @see #killFighter(ArrayList) 
     * @param team1 The team engaging the fight
     * @param team2 The team under attack
     */
    private void teamFight(ArrayList<Student> team1, ArrayList<Student> team2) {
        for (Student f: team1) {
            if (team2.isEmpty()) {
                return;
            }
            Strategy strategy = f.getStrategy();
            strategy = strategy instanceof classes.model.characters.fighters.strategies.Random ? strategy.getStrategy() : strategy;

            if (strategy instanceof Defense) {
                f.fight(this.getFighterWithMinCredit(team1));
            } else {
                f.fight(this.getFighterWithMinCredit(team2));
                this.killFighter(team2);
            }
        }
    }

    /**
     * Get the fighter with the lowest amount of credits from a team.
     *
     * @param team A team of Student
     * @return a Student
     */
    private Student getFighterWithMinCredit(ArrayList<Student> team) {
        return Collections.min(team, Comparator.comparing(Student::getCredits));
    }

    /**
     * Remove the fighters with a credits lower or equal to 0.
     *
     * @param team a team of Student
     */
    public void killFighter(ArrayList<Student> team) {
        ArrayList<Student> temp = new ArrayList<>();
        team.forEach(f -> {
            if (f.getCredits() <= 0) {
                temp.add(f);
            }
        });

        temp.forEach(team::remove);
    }
}
