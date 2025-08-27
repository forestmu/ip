package tasks;

import exceptions.EditTaskErrorException;
import storage.Storage;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> allText = new ArrayList<>();;
    private static ArrayList<String> textToSave = new ArrayList<>();
    private static Storage taskStorage;

    public TaskList(String filePath) {
        this.taskStorage = new Storage(filePath);
        this.textToSave = taskStorage.readToString();
        this.allText = taskStorage.readToTask();
    }

    public static void printList() {
        int max = allText.size();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < max; i++) {
            int order = i + 1;
            Task theTask = allText.get(i);
            System.out.println("    " + order + ". " + theTask.toString());
        }
    }

    public static void doMark(String number, boolean mark) {
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

    public static void delete(String number) {
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
            System.out.println("    Noted. I've removed this task:\n      " + toDelete.toString() +
                    "\n    Now you have " + allText.size() + " tasks left");
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after 'delete'");
        }

    }

    public static void addTask(TaskInformation information) {
        String type = information.getType();
        Task newTask;
        if (type.equals("todo")) {
            newTask = new Todo(information);
        } else if (type.equals("deadline")) {
            newTask = new Deadline(information);
        } else {
            newTask = new Event(information);
        }
        allText.add(newTask);
        textToSave.add(newTask.toSave());
        taskStorage.write(newTask.toSave() + System.lineSeparator(), true);
        System.out.println("    Got it. I've added this task: \n      " +
                newTask.toString() + "\n    Now you have " + allText.size() +
                " tasks in your list.");
    }
}
