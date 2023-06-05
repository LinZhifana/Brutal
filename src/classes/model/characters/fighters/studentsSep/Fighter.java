package classes.model.characters.fighters.studentsSep;

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
    void fight(Student fighter);
}
