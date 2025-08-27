package exceptions;

public class InvalidTimeInputException extends RuntimeException {
    public InvalidTimeInputException() {
        super("OOPS! Please input a proper time in the format: 'dd-mm-yyyy HHmm' ");
    }
}
