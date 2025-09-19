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
        super("Aiyo! Wrong recipe! No sweet is made " + System.lineSeparator()
                + "Follow the following recipes: " + System.lineSeparator()
                + "1. To add sweets (Tasks): " + System.lineSeparator()
                + "     - 'todo <task>'" + System.lineSeparator()
                + "     - 'deadline <task> /by <dd-mm-yyyy HHmm>'" + System.lineSeparator()
                + "     - 'events <task> /from <dd-mm-yyyy HHmm> /to <dd-mm-yyyy HHmm>'"
                + System.lineSeparator()
                + "2. To edit sweets: " + System.lineSeparator()
                + "     - 'mark <task number>' " + System.lineSeparator()
                + "     - 'unmark <task number>'." + System.lineSeparator()
                + "     - 'delete <task number>' " + System.lineSeparator()
                + "     - 'edit <task number> /[same as adding task but without 'todo', 'deadline' or 'event']'"
                + System.lineSeparator()
                + "3. To view/find sweets:" + System.lineSeparator()
                + "     - 'list'" + System.lineSeparator()
                + "     - 'find <keyword>'" + System.lineSeparator()
                + "4. To close:" + System.lineSeparator()
                + "   - 'bye'");
    }
}
