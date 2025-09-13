package exceptions;

/**
 * Exception thrown when user did not specify what command.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidInputException() {
        super("OOPS! Follow the following format: \n"
                + "1. To add Tasks: \n"
                + "     - 'todo <task>'\n"
                + "     - 'deadline <task> /by <dd-mm-yyyy HHmm>'\n"
                + "     - 'events <task> /from <dd-mm-yyyy HHmm> /to <dd-mm-yyyy HHmm>'\n"
                + "2. To edit tasks: \n"
                + "     - 'mark <task number>' \n"
                + "     - 'unmark <task number>'.\n"
                + "     - 'delete <task number>' \n"
                + "     - 'edit <task number> /[same as adding task but without 'todo', 'deadline' or 'event']'\n"
                + "3. To view/find tasks:\n"
                + "     - 'list'\n"
                + "     - 'find <keyword>'\n"
                + "4. To close:"
                + "   - 'bye'");
    }
}
