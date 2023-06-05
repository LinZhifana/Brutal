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
<<<<<<< Updated upstream
     * @see Student
     * @param f1 fighter 1
     * @param f2 fighter 2
     */
    void fight(Student f1, Student f2);
=======
     * @see SStudent
     * @param student fighter 1
     * @param fighter fighter 2
     */
    int fight(classes.model.characters.fighters.studentsSep.Student student, classes.model.characters.fighters.studentsSep.Student fighter);
    int fight(Student f1, Student f2);
>>>>>>> Stashed changes

    /**
     * Get strategy.
     *
     * @return the strategy
     */
    Strategy getStrategy();
}
