package candy;

public class Ui {
    /**
     * Prints the welcome line
     */
    public void printWelcome() {
        System.out.println("Hello! I am Candy.\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye line
     */
    public void printBye() {
        System.out.println("    Bye. Hope to see you again! ^-^");
    }

    /**
     * Prints the error message
     */
    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
