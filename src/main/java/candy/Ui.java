package candy;

/**
 * Represents a class that prints messages to user
 */
public class Ui {
    /**
     * Prints the welcome line
     */
    public static void printWelcome() {
        System.out.println("Hello! I am Candy.\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye line
     */
    public static void printBye() {
        System.out.println("    Bye. Hope to see you again! ^-^");
    }

    /**
     * Prints the error message
     */
    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
