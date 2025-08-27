package exceptions;

/**
 * Exception thrown when an invalid task input is provided.
 */
public class EditTaskErrorException extends RuntimeException {
    public EditTaskErrorException() {
        super("OOPS! Task does not exist");
    }
}
