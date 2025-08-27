package exceptions;

public class NoTaskException extends RuntimeException {
    public NoTaskException() {
        super("OOPS! You did not specify a task!");
    }
}
