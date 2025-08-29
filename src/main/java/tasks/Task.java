package tasks;

public class Task{
    protected String description;
    protected boolean isDone;
    private TaskInformation information;

    /**
     * Constructs a Task
     *
     * @param information TaskInformation object holding information of task
     */
    public Task(TaskInformation information) {
        this.information = information;
        this.description = information.getDescription();
        this.isDone = false;
    }

    /**
     * Constructs a Task
     *
     * @param description String description of the task
     * @param isDone  true if task is completed. False otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns string of isDone
     * 'X' if true
     * empty space if false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks isDone as true
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks isDone as false
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns string of task
     * Used for printing task in storage
     */
    public String toSave() {
        return getStatusIcon() + " | " + this.description;
    }

    /**
     * Returns string of the task
     * Used for printing out task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
