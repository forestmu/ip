package exceptions;

public class InvalidTaskReadException extends RuntimeException {
    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidTaskReadException() {
        super("OOPS! Unable to read tasks from storage");
    }
}
