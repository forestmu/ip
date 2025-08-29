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
     * Prints out all task in allText as string
     */
    public void printList() {
        int max = allText.size();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < max; i++) {
            int order = i + 1;
            Task theTask = allText.get(i);
            System.out.println("    " + order + ". " + theTask.toString());
        }
    }

    /**
     * Marks a task in the array as given mark
     *
     * @param number the index of where task is stored
     * @param mark to mark task as done/undone
     */
    public void doMark(String number, boolean mark) {
        int order;
        try {
            order = Integer.parseInt(number.trim());
            if (order <= 0 || order > allText.size()) {
                throw new EditTaskErrorException();
            }
            Task toMark = allText.get(order - 1);
            if (mark) {
                toMark.markDone();
                System.out.println("    Nice! I've marked this task as done: \n    "
                        + toMark.toString());
            } else {
                toMark.markUndone();
                System.out.println("    Ok, I've marked this task as not done yet: \n    "
                        + toMark.toString());
            }
            textToSave.set(order - 1, toMark.toSave());
            String newList = "";
            for (int i = 0; i < textToSave.size(); i++) {
                newList = newList + textToSave.get(i) + System.lineSeparator();
            }
            taskStorage.write(newList, false);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after 'mark' or 'unmark'");
        }
    }

    /**
     * Deletes a task in array
     *
     * @param number index of where task to delete is stored
     */
    public void delete(String number) {
        int order;
        try {
            order = Integer.parseInt(number.trim());
            if (order <= 0 || order > allText.size()) {
                throw new EditTaskErrorException();
            }
            Task toDelete = allText.get(order - 1);
            allText.remove(order - 1);
            textToSave.remove(order - 1);
            String newList = "";
            for (int i = 0; i < textToSave.size(); i++) {
                newList = newList + textToSave.get(i) + System.lineSeparator();
            }
            taskStorage.write(newList, false);
            System.out.println("    Noted. I've removed this task:\n      "
                    + toDelete.toString() + "\n    Now you have "
                    + allText.size() + " tasks left");
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after 'delete'");
        }

    }

    /**
     * Adds task into array
     *
     * @param information string description of the task
     */
    public void addTask(TaskInformation information) {
        String type = information.getType();
        Task newTask;
        if (type.equals("todo")) {
            newTask = new TodoTask(information);
        } else if (type.equals("deadline")) {
            newTask = new DeadlineTask(information);
        } else {
            newTask = new EventTask(information);
        }
        allText.add(newTask);
        textToSave.add(newTask.toSave());
        taskStorage.write(newTask.toSave() + System.lineSeparator(), true);
        System.out.println("    Got it. I've added this task: \n      "
                + newTask.toString() + "\n    Now you have " + allText.size()
                + " tasks in your list.");
    }


    /**
     * Prints all the task found with keyword
     *
     * @param keyword string description of what task to find
     */
    public void findTask(String keyword) {
        ArrayList<Task> foundList = new ArrayList<>();
        int max = allText.size();

        for (int i = 0; i < max; i++) {
            Task currentTask = allText.get(i);
            String description = currentTask.getDescription().toLowerCase();
            if (description.isEmpty()) {
                continue;
            }
            if (description.contains(keyword.toLowerCase())) {
                foundList.add(currentTask);
            }
        }

        if (foundList.isEmpty()) {
            System.out.println("    No task exist");
        } else {
            System.out.println("    Here are the matching tasks in your list: ");
            int sized = foundList.size();
            for (int i = 0; i < sized; i++) {
                int order = i + 1;
                Task theTask = foundList.get(i);
                System.out.println("    " + order + ". " + theTask.toString());
            }
        }
    }
}
