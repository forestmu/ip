package exceptions;

/**
 * Exception thrown when an invalid time input is provided.
 */
public class InvalidTimeInputException extends RuntimeException {
    public InvalidTimeInputException() {
        super("OOPS! Please input a proper time in the format: 'dd-mm-yyyy HHmm' ");
    }
}
