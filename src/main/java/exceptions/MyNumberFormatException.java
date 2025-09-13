package exceptions;

/**
 * Exception thrown when a non-integer value is inputted
 */
public class MyNumberFormatException extends RuntimeException {
    /**
     * Constructs an error
     * Prints specific message
     */
    public MyNumberFormatException() {
        super("Please input a number after the command to indicate which task to update/delete");
    }
}
