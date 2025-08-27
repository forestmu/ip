package exceptions;

/**
 * Exception thrown when no start time provided if needed.
 */
public class NoStartException extends RuntimeException {
    public NoStartException() {
        super("OOPS! Please input a start time by '/from (start)");
    }
}
