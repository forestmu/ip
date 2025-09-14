package exceptions;

/**
 * Exception thrown when an invalid time input is provided.
 */
public class InvalidTimeInputException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidTimeInputException() {
        super("Candy needs the numbers right!\n"
                + "Please input in the format: 'dd-mm-yyyy HHmm' ");
    }
}
