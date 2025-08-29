package exceptions;

/**
 * Exception thrown when an invalid task input is provided.
 */
public class EditTaskErrorException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public EditTaskErrorException() {
        super("OOPS! Task does not exist");
    }
}
