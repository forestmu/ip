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
        super("Candy needs the recipe right!" + System.lineSeparator()
                + "Please input in the format: 'dd-mm-yyyy HHmm' "
                + System.lineSeparator()
                + "If date/month < 10, remember to add '0' at the front!");
    }
}
