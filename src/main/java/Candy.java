import java.util.ArrayList;
import java.util.Scanner;

public class Candy {
    private static ArrayList<String> allText = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I am Candy🍬.");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("    Bye. Hope to see you again! ^-^");
                break;
            } else if (text.equals(("list"))) {
                int max = allText.size();
                for (int i = 0; i < max; i++) {
                    int order = i + 1;
                    System.out.println("    " + order + ". " + allText.get(i));
                }
            } else {
                allText.add(text);
                System.out.println("    " + text);
            }
        }
    }
}
