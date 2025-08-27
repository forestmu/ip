import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File dataFolder = new File("./data");
    private final File candyStorage = new File("./data/candyStorage.txt");

    public Storage() {
        try {
            //makes folder and file if doesn't exist
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            if (!candyStorage.exists()) {
                candyStorage.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String string, boolean toAppend) {
        try {
            FileWriter writer = new FileWriter(candyStorage, toAppend);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> readToString() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(candyStorage);
            while (scanner.hasNextLine()) {
                String toAdd = scanner.nextLine();
                list.add(toAdd);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Task> readToTask() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(candyStorage);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task;

                String[] parts = taskString.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("X");
                String description = parts[2].trim();

                //create the task
                if (type.equals("T")) {
                    task = new Todo(description, isDone);
                } else if (type.equals("D")) {
                    String end = parts[3];
                    task = new Deadline(description, isDone, end);
                } else {
                    String start = parts[3];
                    String end = parts[4];
                    task = new Event(description, isDone, start, end);
                }
                list.add(task);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
