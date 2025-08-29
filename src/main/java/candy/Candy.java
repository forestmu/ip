package candy;

import tasks.TaskList;

import java.util.Scanner;

/**
 * Represents a Candy chatbox.
 */
public class Candy {
    private TaskList taskList;

    /**
     * Constructs a Candy.
     *
     * @param filePath The path of the storage file.
     */
    public Candy(String filePath) {
        this.taskList = new TaskList(filePath);
    }

    /**
     * Runs the Candy program.
     *
     *
     */
    public void run() {
        boolean isContinue = true;
        Ui.printWelcome();

        Scanner scanner = new Scanner(System.in);
        while (isContinue) {
            String text = scanner.nextLine();
            isContinue = Parser.parse(text, taskList);
        }
    }

    /**
     * Entry point of Candy
     *
     * @param args not used
     */
    public static void main(String[] args) {
        new Candy("./data/candyStorage.txt").run();
    }
}
