package classes.model.characters.fighters.strategies;
import classes.model.characters.fighters.students.Student;

/**
 * The interface that the different strategies have to implement.
 *
 * @version 1.0
 */
public interface Strategy {
    /**
     * Make a fighter fight another one.
     *
     * @see Student
     * @param f1 fighter 1
     * @param f2 fighter 2
     */
    void fight(Student f1, Student f2);

    /**
     * Get strategy.
     *
     * @return the strategy
     */
    Strategy getStrategy();
}
