import java.util.ArrayList;
import java.util.Scanner;

public class Candy {
    private static ArrayList<Task> allText = new ArrayList<>();

    private static void listing() {
        int max = allText.size();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < max; i++) {
            int order = i + 1;
            Task theTask = allText.get(i);
            System.out.println("    " + order + ". " + theTask.toString());
        }
    }

    private static void marking(String number, boolean mark) {
        int order = Integer.parseInt(number);
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
    }

    private static void addTask(String text, String type, String start, String end) {
        Task newTask;
        if (type.equals("todo")) {
            newTask = new Todo(text);
        } else if (type.equals("deadline")) {
            newTask = new Deadline(text, end);
        } else if (type.equals("event")){
            newTask = new Event(text, start, end);
        } else {
            newTask = new Task(text);
        }
        allText.add(newTask);
        System.out.println("    Got it. I've added this task: \n      " +
                newTask.toString() + "\n    Now you have " + allText.size() +
                " tasks in your list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I am Candy🍬.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("    Bye. Hope to see you again! ^-^");
                break;
            } else if (text.equals(("list"))) {
                listing();
            } else if (text.startsWith("mark ")) {
                String number = text.substring(5);
                marking(number, true);
            } else if (text.startsWith("unmark ")) {
                String number = text.substring(7);
                marking(number, false);
            }  else {
                String description, type, start, end;
                if (text.startsWith("todo")) {
                    description = text.substring(5);
                    type = "todo";
                    start = null;
                    end = null;
                } else if (text.startsWith("deadline")) {
                    int index = text.indexOf("/");

                    description = text.substring(9, index - 1);
                    type = "deadline";
                    start = null;
                    end = text.substring(index + 4);
                } else if (text.startsWith("event")) {
                    int getFrom = text.indexOf("/");
                    int getTo = text.lastIndexOf("/");

                    description = text.substring(6, getFrom - 1);
                    type = "event";
                    start = text.substring(getFrom + 6, getTo - 1);
                    end = text.substring(getTo + 4);
                } else {
                    description = text;
                    type = "null";
                    start = null;
                    end = null;
                }
                addTask(description, type, start, end);
            }
        }
    }
}
