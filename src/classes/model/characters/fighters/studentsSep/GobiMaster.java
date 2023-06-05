package classes.model.characters.fighters.studentsSep;

/**
 * Represents a Gobi Master.
 *
 * @version 1.0
 */
public class GobiMaster extends Student {
    /**
     * Create a gobi master with the specified name and the default characteristics.
     *
     * @param firstname the student's firstname
     * @param lastName the student's lastname
     */
    public GobiMaster(
            String firstname,
            String lastName
    ) {
        super(firstname, lastName, 2, 2, 2, 10, 2);
    }
}
