package tasks;

import exceptions.EditTaskErrorException;
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
     * Returns a string notifying that a task in the array
     * is marked or unmarked
     *
     * @param number the index of where task is stored
     * @param mark to mark task as done/undone
     */
    public String doMark(String number, boolean mark) {
        int order;
        try {
            order = Integer.parseInt(number.trim());
            if (order <= 0 || order > allText.size()) {
                throw new EditTaskErrorException();
            }

            //marks the specified task
            Task toMark = allText.get(order - 1);
            String dialog;
            if (mark) {
                toMark.markDone();
                dialog = "Nice! I've marked this task as done: \n    ";
            } else {
                toMark.markUndone();
                dialog = "Ok, I've marked this task as not done yet: \n    " ;
            }

            //edit the task in the array
            textToSave.set(order - 1, toMark.toSave());
            //update the string of tasks
            String newList = "";
            for (int i = 0; i < textToSave.size(); i++) {
                newList = newList + textToSave.get(i) + System.lineSeparator();
            }
            //save to storage
            taskStorage.write(newList, false);
            return dialog + toMark.toString();
        } catch (NumberFormatException e) {
            return "Please input a number after 'mark' or 'unmark'";
        }
    }

    /**
     * Returns string notifying a task is deleted from array
     *
     * @param number index of where task to delete is stored
     */
    public String delete(String number) {
        int order;
        try {
            order = Integer.parseInt(number.trim());
            if (order <= 0 || order > allText.size()) {
                throw new EditTaskErrorException();
            }

            //update the array
            Task toDelete = allText.get(order - 1);
            allText.remove(order - 1);
            textToSave.remove(order - 1);

            //update the string of tasks
            String newList = "";
            for (int i = 0; i < textToSave.size(); i++) {
                newList = newList + textToSave.get(i) + System.lineSeparator();
            }
            //save to storage
            taskStorage.write(newList, false);

            return "Noted. I've removed this task:\n      "
                    + toDelete.toString() + "\n    Now you have "
                    + allText.size() + " tasks left";
        } catch (NumberFormatException e) {
            return "Please input a number after 'delete'";
        }

    }

    /**
     * Returns string to notify that task is added
     *
     * @param text string description of the task
     */
    public String addTask(String text, String type) {
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
    }


    /**
     * Returns string of all tasks found with keyword
     *
     * @param keyword string description of what task to find
     */
    public String findTask(String keyword) {
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
}
