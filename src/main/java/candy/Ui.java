package candy;

public class Ui {
    public void printWelcome() {
        System.out.println("Hello! I am Candy.\nWhat can I do for you?");
    }

    public void printBye() {
        System.out.println("    Bye. Hope to see you again! ^-^");
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
