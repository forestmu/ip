package tasks;

import candy.Ui;
import exceptions.*;
import storage.Storage;
import java.util.ArrayList;

/**
 * Represents all the tasks
 */
public class TaskList {
    private ArrayList<Task> allText;
    private ArrayList<String> textToSave;
    private Storage taskStorage;

    /**
     * Constructor to initialise array and storage
     *
     * @param filePath string description of file path
     */
    public TaskList(String filePath) {
        this.taskStorage = new Storage(filePath);
        this.textToSave = taskStorage.readToString();
        this.allText = taskStorage.readToTask();
    }

    /**
     * Rewrites the tasks in the storage
     * to the updated version
     */
    public void overwriteStorage() {
        //update the string of tasks
        String newList = "";
        for (int i = 0; i < textToSave.size(); i++) {
            newList = newList + textToSave.get(i) + System.lineSeparator();
        }
        //save to storage
        taskStorage.write(newList, false);
    }

    /**
     * Returns string of all tasks in allText
     */
    public String printList() {
        int max = allText.size();
        String toReturn = "Here are the tasks in your list: " + System.lineSeparator();
        for (int i = 0; i < max; i++) {
            int order = i + 1;
            Task theTask = allText.get(i);
            toReturn = toReturn + order + ". " + theTask.toString() + System.lineSeparator();
        }
        return toReturn;
    }

    /**
     * Reads the task number that user wants to update/delete
     *
     * @param order String format of the "integer" user input
     * @return      task number in Integer
     */
    public int getTaskNumber(String order) {
        try {
            int taskNumber = Integer.parseInt(order.trim());
            if (taskNumber <= 0 || taskNumber > allText.size()) {
                throw new EditTaskErrorException();
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException();
        }
    }

    /**
     * Returns a string notifying that a task in the array
     * is marked or unmarked
     *
     * @param text the index of where task is stored
     * @param mark to mark task as done/undone
     */
    public String doMark(String text, boolean mark) {
        try {
            String number;
            if (mark) {
                number = text.substring(4);
            } else {
                number = text.substring(6);
            }
            int order = getTaskNumber(number);

            //marks the specified task
            Task toMark = allText.get(order - 1);
            String dialog;
            if (mark) {
                toMark.markDone();
                assert toMark.getStatusIcon().equals("X") : "task should be marked done";
                dialog = "Nice! I've marked this task as done: \n    ";
            } else {
                toMark.markUndone();
                assert toMark.getStatusIcon().equals(" ") : "task should be marked undone";
                dialog = "Ok, I've marked this task as not done yet: \n    " ;
            }

            //edit the task in the array
            textToSave.set(order - 1, toMark.toSave());
            overwriteStorage();
            return dialog + toMark.toString();
        } catch (MyNumberFormatException | EditTaskErrorException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Returns string notifying a task is deleted from array
     *
     * @param text Users input
     */
    public String delete(String text) {
        try {
            String number = text.substring(6);
            int order;
            order = getTaskNumber(number);

            //update the array
            Task toDelete = allText.get(order - 1);
            allText.remove(order - 1);
            textToSave.remove(order - 1);

            overwriteStorage();

            return "Noted. I've removed this task:\n      "
                    + toDelete.toString() + "\n    Now you have "
                    + allText.size() + " tasks left";
        } catch (MyNumberFormatException | EditTaskErrorException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Returns string to notify that task is added
     *
     * @param text string description of the task
     */
    public String addTask(String text, String type) {
        try {
            //create the task
            TaskInformation information = new TaskInformation(text, type);
            Task newTask;
            if (type.equals("todo")) {
                newTask = new TodoTask(information);
            } else if (type.equals("deadline")) {
                newTask = new DeadlineTask(information);
            } else {
                newTask = new EventTask(information);
            }

            //add to array
            allText.add(newTask);
            textToSave.add(newTask.toSave());

            //save to storage
            taskStorage.write(newTask.toSave() + System.lineSeparator(), true);
            return "Got it. I've added this task: \n      "
                    + newTask.toString() + "\n    Now you have " + allText.size()
                    + " tasks in your list.";
        } catch (NoEndException | NoStartException
                 | NoTaskException | InvalidTimeInputException e) {
            return Ui.printError(e);
        }
    }


    /**
     * Returns string of all tasks found with keyword
     *
     * @param text string description of the command
     */
    public String findTask(String text) {
        String keyword = text.substring(4).trim();
        if (keyword.isEmpty()) {
            return "Please provide a keyword to search for.";
        }

        ArrayList<Task> foundList = new ArrayList<>();
        int max = allText.size();

        //loop through all the task
        for (int i = 0; i < max; i++) {
            Task currentTask = allText.get(i);
            String description = currentTask.getDescription().toLowerCase();

            //should not happen:
            if (description.isEmpty()) {
                continue;
            }

            //check if it is what user is finding for and add
            if (description.contains(keyword.toLowerCase())) {
                foundList.add(currentTask);
            }
        }

        if (foundList.isEmpty()) {
            return "No task exist";
        }

        //convert the array of found tasks into string
        String toReturn = "Here are the matching tasks in your list: " + System.lineSeparator();
        int sized = foundList.size();
        for (int i = 0; i < sized; i++) {
            int order = i + 1;
            Task theTask = foundList.get(i);
            toReturn = toReturn + order + ". " + theTask.toString() + System.lineSeparator();
        }
        return toReturn;
    }

    /**
     * Edits the information in the given task
     *
     * @param newInfo The String of new information users typed
     *                in
     * @param toEdit The Task to update
     */
    public void editInformation(String newInfo, Task toEdit) {
        String type = toEdit.getType();
        String fullText = type + " " + newInfo;

        TaskInformation temporary = new TaskInformation(fullText, type);
        String description = temporary.getDescription();
        String start = temporary.getStartString();
        String end = temporary.getEndString();

        toEdit.setText(newInfo);
        toEdit.setDescription(description);
        if (type.equals("deadline") || type.equals("event")) {
            toEdit.setEndTime(end);
        }

        if (type.equals("event")) {
            toEdit.setStartTime(start);
        }
    }

    /**
     * Updates the tasks in the storage
     */
    public String updateTask(String text) {
        int order;
        try {
            //String after the edit word
            String details = text.substring(4);

            //get index of the task to edit
            int detailStart = details.indexOf("/");
            if (detailStart == -1) {
                throw new InvalidInputException();
            }

            //get task
            String number = details.substring(0, detailStart).trim();
            order = getTaskNumber(number);
            Task toEdit = allText.get(order - 1);

            //String after specifying which task to edit
            String taskDetails = details.substring(detailStart + 1).trim();

            //edit the task
            editInformation(taskDetails, toEdit);
            textToSave.set(order - 1, toEdit.toSave());
            overwriteStorage();

            return "I have updated the following task to: \n"
                    + toEdit.toString();
        } catch (MyNumberFormatException | NoTaskException | NoStartException
                 | NoEndException | InvalidInputException
                 | InvalidTimeInputException | EditTaskErrorException e) {
            return Ui.printError(e);
        }
    }
}
