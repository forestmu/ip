package exceptions;

/**
 * Exception thrown when user did not specify what command.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("OOPS! If you are adding in a task, please start with 'todo', 'deadline' or 'event'.\n" +
                "Add a '/by (end)' for deadline or '/from (start) /to (end)' for events\n" +
                "Else, start with 'mark' or 'unmark' to edit task.\n" +
                "Start with 'delete' to delete task");
    }
}
