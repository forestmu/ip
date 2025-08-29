package exceptions;

/**
 * Exception thrown when no task provided.
 */
public class NoTaskException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public NoTaskException() {
        super("OOPS! You did not specify a task!");
    }
}
