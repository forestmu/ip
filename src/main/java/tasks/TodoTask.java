package tasks;

public class TodoTask extends Task {
    public TodoTask(TaskInformation information) {
        super(information);
    }

    public TodoTask(String description, boolean isDone) {
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
