public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("OOPS! Please start with 'todo', 'deadline' or 'event' and input the task.\n" +
                "Add a '/by (end)' for deadline or '/from (start) /to (end)' for events");
    }
}
