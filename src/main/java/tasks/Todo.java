package tasks;

public class Todo extends Task {
    public Todo(TaskInformation information) {
        super(information);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }
}
