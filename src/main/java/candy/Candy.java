package candy;

import tasks.TaskList;

import java.util.Scanner;

/**
 * Represents a Candy chatbox.
 */
public class Candy {
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructs a Candy.
     *
     * @param filePath The path of the storage file.
     */
    public Candy(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList(filePath);
        this.parser = new Parser();
    }

    /**
     * Runs the Candy program.
     *
     *
     */
    public void run() {
        ui.printWelcome();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            parser.parse(text, taskList, ui);
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
