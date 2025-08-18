import java.util.ArrayList;
import java.util.Scanner;

public class Candy {
    private static ArrayList<Task> allText = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I am Candy🍬.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            Task newTask = new Task(text);
            if (text.equals("bye")) {
                System.out.println("    Bye. Hope to see you again! ^-^");
                break;
            } else if (text.equals(("list"))) {
                int max = allText.size();
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < max; i++) {
                    int order = i + 1;
                    Task theTask = allText.get(i);
                    System.out.println("    " + order + ". " + theTask.toString());
                }
            } else if (text.startsWith("mark ")) {
                String number = text.substring(5);
                int order = Integer.parseInt(number);
                Task toMark = allText.get(order - 1);
                toMark.markDone();
                System.out.println("    Nice! I've marked this task as done: \n    "
                        + toMark.toString());
            } else if (text.startsWith("unmark ")) {
                    String number = text.substring(7);
                    int order = Integer.parseInt(number);
                    Task toMark = allText.get(order - 1);
                    toMark.markUndone();
                    System.out.println("    Ok, I've marked this task as not done yet: \n    "
                            + toMark.toString());
            } else {
                allText.add(newTask);
                System.out.println("    " + text);
            }
        }
    }
}
