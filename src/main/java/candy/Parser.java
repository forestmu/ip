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
     * @param ui ui instance
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
    public void parse(String text, TaskList taskList, Ui ui) {
        if (text.equals("bye")) {
            ui.printBye();
        } else if (text.equals(("list"))) {
            taskList.printList();
        } else if (text.startsWith("mark")) {
            String number = text.substring(4);
            try {
                taskList.doMark(number, true);
            } catch (EditTaskErrorException e) {
                ui.printError(e);
            }
        } else if (text.startsWith("unmark")) {
            String number = text.substring(6);
            try {
                taskList.doMark(number, false);
            } catch (EditTaskErrorException e) {
                ui.printError(e);
            }
        } else if (text.startsWith("delete")) {
            String number = text.substring(6);
            try {
                taskList.delete(number);
            } catch (EditTaskErrorException e) {
                ui.printError(e);
            }
        } else if (text.startsWith("find")) {
            String keyword = text.substring(4).trim();
            if (keyword.isEmpty()) {
                System.out.println("Please provide a keyword to search for.");
            } else {
                taskList.findTask(keyword);
            }
        } else {
            try {
                taskList.addTask(text);
            } catch (InvalidInputException e) {
                ui.printError(e);
            } catch (NoStartException e) {
                ui.printError(e);
            } catch (NoEndException e) {
                ui.printError(e);
            } catch (NoTaskException e) {
                ui.printError(e);
            } catch (InvalidTimeInputException e) {
                ui.printError(e);
            }
        }
    }
}
