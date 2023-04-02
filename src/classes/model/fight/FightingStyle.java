package classes.model.fight;

import classes.model.characters.fighters.students.Student;

import java.util.ArrayList;

/**
 * The interface that the different fighting styles have to implement.
 */
public interface FightingStyle {
    /**
     * Make the teams fight each other.
     *
     * @param team1 team 1
     * @param team2 team 2
     */
    void fight(ArrayList<Student> team1, ArrayList<Student> team2);
}
