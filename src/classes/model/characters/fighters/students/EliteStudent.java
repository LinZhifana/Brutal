package classes.model.characters.fighters.students;

/**
 * Represents an Elite student.
 *
 * @version 1.0
 */
public class EliteStudent extends Student {
    /**
     * Create an elite student with the specified name and the default characteristics.
     *
     * @param firstname the student's firstname
     * @param lastName the student's lastname
     */
    public EliteStudent(
            String firstname,
            String lastName
    ) {
        super(firstname, lastName, 1, 1, 1, 5, 1);
    }
}
