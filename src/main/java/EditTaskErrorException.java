public class EditTaskErrorException extends RuntimeException {
    public EditTaskErrorException() {
        super("OOPS! Task does not exist");
    }
}
