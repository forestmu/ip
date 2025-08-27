package exceptions;

public class NoStartException extends RuntimeException {
    public NoStartException() {
        super("OOPS! Please input a start time by '/from (start)");
    }
}
