package tasks;

import time.Time;

/**
 * Represents a task with a deadline.
 * Extends Task with a deadline
 */
public class DeadlineTask extends Task {
    protected Time by;

    /**
     * Constructor of a Deadline task
     *
     * @param information TaskInformation object holding information of task
     */
    public DeadlineTask(TaskInformation information) {
        super(information);
        this.by = information.getEndTime();
    }

//    /**
//     * Constructor of a deadline task
//     *
//     * @param description String description of the task
//     * @param by  Time object of the due date
//     * @param isDone  true if task is completed. False otherwise
//     */
//    public DeadlineTask(String description, boolean isDone, Time by) {
//        super(description, isDone);
//        this.by = by;
//    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.toPrint() + ")";
    }

    @Override
    public String toSave() {
        return "D | " + super.toSave() + " | " + by.toString();
    }

    @Override
    public void setEndTime(String end) {
        super.setEndTime(end);
        this.by = super.getEndTime();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }
}