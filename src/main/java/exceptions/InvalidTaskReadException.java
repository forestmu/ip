package exceptions;

public class InvalidTaskReadException extends RuntimeException {
    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidTaskReadException() {
        super("Oh no! Your stored candy has melted. I can't distinguish them!");
    }
}
