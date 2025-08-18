public class MarkingErrorException extends RuntimeException {
    public MarkingErrorException() {
        super("OOPS! Task does not exist");
    }
}
