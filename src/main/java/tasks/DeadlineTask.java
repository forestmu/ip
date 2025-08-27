package tasks;

import time.Time;

/**
 * Represents a task with a deadline.
 * Extends Task with a deadline
 */
public class DeadlineTask extends Task {
    protected Time by;

    /**
     * Constructor of Deadline
     *
     * @param information TaskInformation object holding information of task
     */
    public DeadlineTask(TaskInformation information) {
        super(information);
        this.by = information.getEnd();
    }

    /**
     * Constructor of deadline
     *
     * @param description String description of the task
     * @param by  Time object of the due date
     * @param isDone  true if task is completed. False otherwise
     */
    public DeadlineTask(String description, boolean isDone, Time by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns string of the task
     * Used for printing out task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.toPrint() + ")";
    }

    /**
     * Returns string of task
     * Used for printing task in storage
     */
    @Override
    public String toSave() {
        return "D | " + super.toSave() + " | " + by.toString();
    }
}