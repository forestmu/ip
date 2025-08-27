package exceptions;

public class NoEndException extends RuntimeException {
    public NoEndException() {
        super("OOPS! Please specify an end time by inputting" +
                " '/by (end)' for deadline task or '/to (end)' for event task");
    }
}
