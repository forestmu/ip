import java.util.Scanner;

public class Candy {
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        System.out.println("Hello! I am Candy.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("    Bye. Hope to see you again! ^-^");
                break;
            } else if (text.equals(("list"))) {
                taskList.printList();
            } else if (text.startsWith("mark")) {
                String number = text.substring(4);
                try {
                    taskList.doMark(number, true);
                } catch (EditTaskErrorException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("unmark")) {
                String number = text.substring(6);
                try {
                    taskList.doMark(number, false);
                } catch (EditTaskErrorException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("delete")) {
                String number = text.substring(6);
                try {
                    taskList.delete(number);
                } catch (EditTaskErrorException e) {
                    System.out.println(e.getMessage());
                }
            }  else {
                try {
                    TaskInformation information = new TaskInformation(text);
                    taskList.addTask(information);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                } catch (NoStartException e) {
                    System.out.println(e.getMessage());
                } catch (NoEndException e) {
                    System.out.println(e.getMessage());
                } catch (NoTaskException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidTimeInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
