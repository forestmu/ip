package storage;

import candy.Ui;
import exceptions.InvalidTaskReadException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TodoTask;
import tasks.Task;
import time.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage that handle edits to file
 */
public class Storage {
    private File dataFolder;
    private File candyStorage;

    /**
     * Constructs a storage file
     *
     * @param filePath path to the storage file
     * @throws IOException if file cannot be created
     */
    public Storage(String filePath) {
        try {
            this.candyStorage = new File(filePath);
            this.dataFolder = candyStorage.getParentFile();
            //makes folder and file if doesn't exist
            if (dataFolder != null && !dataFolder.exists()) {
                dataFolder.mkdir();
            }
            if (!candyStorage.exists()) {
                candyStorage.createNewFile();
            }
        } catch (IOException e) {
            Ui.printError(e);
        }
    }


    /**
     * Writes to storage file
     *
     * @param string string description of the task
     * @param toAppend true to add on, false to overwrite the current file
     */
    public void write(String string, boolean toAppend) {
        try {
            FileWriter writer = new FileWriter(candyStorage, toAppend);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     * Returns arraylist of the tasks as String
     */
    public ArrayList<String> readToString() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(candyStorage);
            //scan each line in the file for task
            while (scanner.hasNextLine()) {
                String toAdd = scanner.nextLine();
                list.add(toAdd);
            }
            scanner.close();
        } catch (Exception e) {
            Ui.printError(e);
        }
        return list;
    }

    /**
     * Returns arraylist of the tasks as Task
     */
    public ArrayList<Task> readToTask() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(candyStorage);
            //scan each line in the file for task
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task;

                //Splits information into type, whether it's done and description
                //universal for todo, deadline and event tasks
                String[] parts = taskString.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("X");
                String description = parts[2].trim();

                //create the task
                if (type.equals("T")) {
                    task = new TodoTask(description, isDone);
                } else if (type.equals("D")) {
                    //deadline task has end time
                    String end = parts[3].trim();
                    Time endTime = new Time(end);
                    task = new DeadlineTask(description, isDone, endTime);
                } else if (type.equals("E")) {
                    //event task have start and end time
                    String start = parts[3].trim();
                    String end = parts[4].trim();
                    Time startTime = new Time(start);
                    Time endTime = new Time(end);
                    task = new EventTask(description, isDone, startTime, endTime);
                } else {
                    //should not reach here
                    throw new InvalidTaskReadException();
                }

                list.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException | InvalidTaskReadException e) {
            Ui.printError(e);
        }
        return list;
    }
}
