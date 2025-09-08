package tasks;

/**
 * Represents a task.
 */
public class TodoTask extends Task {

    /**
     * Constructor of Todo task
     *
     * @param information TaskInformation object holding information of task
     */
    public TodoTask(TaskInformation information) {
        super(information);
    }

//    /**
//     * Constructor of Todo task
//     *
//     * @param description String description of the task
//     * @param isDone  true if task is completed. False otherwise
//     */
//    public TodoTask(String description, boolean isDone) {
//        super(description, isDone);
//    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }
}
