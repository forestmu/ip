import java.util.Scanner;

public class Candy {
    private TaskList taskList;
    private Ui ui;

    public Candy(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList(filePath);
    }

    public void run() {
        ui.printWelcome();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                ui.printBye();
                break;
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
            }  else {
                try {
                    TaskInformation information = new TaskInformation(text);
                    taskList.addTask(information);
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

    public static void main(String[] args) {
        new Candy("./data/candyStorage.txt").run();
    }
}
