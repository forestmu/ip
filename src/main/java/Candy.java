import java.util.ArrayList;
import java.util.Scanner;

public class Candy {
    private static ArrayList<Task> allText = new ArrayList<>();

    private static void listing() {
        int max = allText.size();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < max; i++) {
            int order = i + 1;
            Task theTask = allText.get(i);
            System.out.println("    " + order + ". " + theTask.toString());
        }
    }

    private static void marking(String number, boolean mark) {
        int order;
        try {
            order = Integer.parseInt(number.trim());
            if (order <= 0 || order > allText.size()) {
                throw new EditTaskErrorException();
            }
            Task toMark = allText.get(order - 1);
            if (mark) {
                toMark.markDone();
                System.out.println("    Nice! I've marked this task as done: \n    "
                        + toMark.toString());
            } else {
                toMark.markUndone();
                System.out.println("    Ok, I've marked this task as not done yet: \n    "
                        + toMark.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after 'mark' or 'unmark'");
        }
    }

    private static void delete(String number) {
        int order;
        try {
            order = Integer.parseInt(number.trim());
            if (order <= 0 || order > allText.size()) {
                throw new EditTaskErrorException();
            }
            Task toDelete = allText.get(order - 1);
            allText.remove(order - 1);
            System.out.println("    Noted. I've removed this task:\n      " + toDelete.toString() +
                            "\n    Now you have " + allText.size() + " tasks left");
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after 'delete'");
        }

    }

    private static void addTask(String text, String type, String start, String end) {
        Task newTask;
        if (type.equals("todo")) {
            newTask = new Todo(text);
        } else if (type.equals("deadline")) {
            newTask = new Deadline(text, end);
        } else {
            newTask = new Event(text, start, end);
        }
        allText.add(newTask);
        System.out.println("    Got it. I've added this task: \n      " +
                newTask.toString() + "\n    Now you have " + allText.size() +
                " tasks in your list.");
    }

    private static String getType(String text) {
        String type;
        if (text.startsWith("todo")) {
            type = "todo";
        } else if (text.startsWith("deadline")) {
            type = "deadline";
        } else if (text.startsWith("event")) {
            type = "event";
        } else {
            type = null;
        }

        if (type == null) {
            throw new InvalidInputException();
        } else {
            return type;
        }
    }

    private static String getDescription(String text) {
        String description;
        if (text.startsWith("todo")) {
            description = text.substring(4);
        } else if (text.startsWith("deadline")) {
            int index = text.indexOf("/");
            if (index == -1) {
                throw new InvalidInputException();
            }
            description = text.substring(8, index - 1);
        } else if (text.startsWith("event")) {
            int getFrom = text.indexOf("/");
            if (getFrom == -1) {
                throw new InvalidInputException();
            }
            description = text.substring(5, getFrom - 1);
        } else {
            description = text;
        }

        if (description.isBlank()) {
            throw new NoTaskException();
        } else {
            return description;
        }
    }

    private static String getStart(String text) {
        String start;
        if (text.startsWith("event")) {
            int getFrom = text.indexOf("/");
            int getTo = text.lastIndexOf("/");
            if (getFrom == getTo || getFrom == -1 || getTo == -1) {
                throw new InvalidInputException();
            }
            start = text.substring(getFrom + 5, getTo - 1);
            if (start.isBlank()) {
                throw new NoStartException();
            }
        } else {
            start = null;
        }
        return start;
    }

    private static String getEnd(String text) {
        String end;
        if (text.startsWith("deadline")) {
            int index = text.indexOf("/");
            if (index == -1) {
                throw new InvalidInputException();
            }
            end = text.substring(index + 3);
            if (end.isBlank()) {
                throw new NoEndException();
            }
        } else if (text.startsWith("event")) {
            int index = text.lastIndexOf("/");
            if (index == -1) {
                throw new InvalidInputException();
            }
            end = text.substring(index + 3);
            if (end.isBlank()) {
                throw new NoEndException();
            }
        } else {
            end = null;
        }
        return end;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I am Candy.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("    Bye. Hope to see you again! ^-^");
                break;
            } else if (text.equals(("list"))) {
                listing();
            } else if (text.startsWith("mark")) {
                String number = text.substring(4);
                try {
                    marking(number, true);
                } catch (EditTaskErrorException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("unmark")) {
                String number = text.substring(6);
                try {
                    marking(number, false);
                } catch (EditTaskErrorException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("delete")) {
                String number = text.substring(6);
                try {
                    delete(number);
                } catch (EditTaskErrorException e) {
                    System.out.println(e.getMessage());
                }
            }  else {
                try {
                    String type = getType(text);
                    String start = getStart(text);
                    String end = getEnd(text);
                    String description = getDescription(text);
                    addTask(description, type, start, end);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                } catch (NoStartException e) {
                    System.out.println(e.getMessage());
                } catch (NoEndException e) {
                    System.out.println(e.getMessage());
                } catch (NoTaskException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
