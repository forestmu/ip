public class Task{
    protected String description;
    protected boolean isDone;
    private TaskInformation information;

    public Task(TaskInformation information) {
        this.information = information;
        this.description = information.getDescription();
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toSave() {
        return getStatusIcon() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
