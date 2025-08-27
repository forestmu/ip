package tasks;

/**
 * Represents a task.
 */
public class TodoTask extends Task {

    /**
     * Constructor of Todo
     *
     * @param information TaskInformation object holding information of task
     */
    public TodoTask(TaskInformation information) {
        super(information);
    }

    /**
     * Constructor of Todo
     *
     * @param description String description of the task
     * @param isDone  true if task is completed. False otherwise
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string of the task
     * Used for printing out task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string of task
     * Used for printing task in storage
     */
    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }
}
