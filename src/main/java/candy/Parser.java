package candy;

import exceptions.EditTaskErrorException;
import exceptions.NoEndException;
import exceptions.NoStartException;
import exceptions.NoTaskException;
import exceptions.InvalidInputException;
import exceptions.InvalidTimeInputException;

import tasks.TaskList;

/**
 * Represents a parser to parse user command
 */
public class Parser {


    /**
     * Parse the command
     *
     * @param text String description of what user types
     * @param taskList List of task of current candy
     *
     * @throws EditTaskErrorException when task does not exist
     * @throws InvalidInputException when input did not start with the key words
     * @throws InvalidTimeInputException when time is not keyed in the right format
     * @throws NoTaskException when there is missing tasks
     * @throws NoStartException when there is missing start time
     * @throws NoEndException when there is missing end time
     * *
     */
    public static boolean parse(String text, TaskList taskList) {
        Command commandWord;
        try {
            commandWord = Command.fromInput(text);
        } catch (InvalidInputException e) {
            Ui.printError(e);
            return true;
        }

        String number;
        switch (commandWord) {
        case BYE:
            Ui.printBye();
            return false;
        case LIST:
            taskList.printList();
            break;
        case MARK:
            number = text.substring(4);
            try {
                taskList.doMark(number, true);
            } catch (EditTaskErrorException e) {
                Ui.printError(e);
            }
            break;
        case UNMARK:
            number = text.substring(6);
            try {
                taskList.doMark(number, false);
            } catch (EditTaskErrorException e) {
                Ui.printError(e);
            }
            break;
        case DELETE:
            number = text.substring(6);
            try {
                taskList.delete(number);
            } catch (EditTaskErrorException e) {
                Ui.printError(e);
            }
        case FIND:
            String keyword = text.substring(4).trim();
            if (keyword.isEmpty()) {
                System.out.println("Please provide a keyword to search for.");
            } else {
                taskList.findTask(keyword);
            }
            break;
        case TODO:
            try {
                taskList.addTask(text, "todo");
            } catch (NoEndException | NoStartException
                     | NoTaskException | InvalidTimeInputException e) {
                Ui.printError(e);
            }
            break;
        case DEADLINE:
            try {
                taskList.addTask(text, "deadline");
            } catch (NoEndException | NoStartException
                     | NoTaskException | InvalidTimeInputException e) {
                Ui.printError(e);
            }
            break;
        case EVENT:
            try {
                taskList.addTask(text, "event");
            } catch (NoEndException | NoStartException
                     | NoTaskException | InvalidTimeInputException e) {
                Ui.printError(e);
            }
            break;
        }
        return true;
    }
}
