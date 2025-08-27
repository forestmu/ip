public class InvalidTimeInput extends RuntimeException {
    public InvalidTimeInput() {
        super("OOPS! Please input in a proper time format: 'dd-mm-yyyy HHmm' ");
    }
}
