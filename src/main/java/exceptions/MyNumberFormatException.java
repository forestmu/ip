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
        super("Candy can't tell which task. \n"
                + "Give candy a number after your command");
    }
}
