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
        super("Oh no! Task does not exist \uD83D\uDE22");
    }
}
