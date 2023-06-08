package classes.model.characters.fighters.students;

/**
 * The interface that the different fighter have to implement.
 *
 * @version 1.0
 */
public interface Fighter {
    /**
     * Fight another fighter.
     *
     * @param fighter The fighter to combat
     */
    int fight(Student fighter);
}
