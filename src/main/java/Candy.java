import java.util.Scanner;

public class Candy {
    public static void main(String[] args) {
        System.out.println("Hello! I am Candy🍬.");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        System.out.println("    " + text);
        while (true) {
            text = scanner.nextLine();
            if (!text.equals("bye")) {
                System.out.println("    " + text);
            } else {
                System.out.println("    Bye. Hope to see you again! ^-^");
                break;
            }
        }
    }
}
