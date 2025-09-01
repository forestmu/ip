package candy;

/**
 * Represents a class that prints messages to user
 */
public class Ui {
    /**
     * Returns String of the welcome line
     */
    public static String printWelcome() {
        return "Hello! I am Candy. ^-^ \nWhat can I do for you?)";
    }

    /**
     * Returns String of the goodbye line
     */
    public static String printBye() {
        return "Bye. Hope to see you again! ^-^";
    }

    /**
     * Returns String of the error message
     */
    public static String printError(Exception e) {
        return e.getMessage();
    }
}
