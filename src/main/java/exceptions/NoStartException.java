package exceptions;

/**
 * Exception thrown when no start time provided if needed.
 */
public class NoStartException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public NoStartException() {
        super("OOPS! Please input a start time by '/from (start)");
    }
}
