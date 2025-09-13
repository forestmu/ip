package tasks;

import time.Time;

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
     * Returns the type
     */
    public String getType() {
        return this.information.getType();
    }

    /**
     * Updates the description
     */
    public void setDescription(String description) {
        this.information.setDescription(description);
        this.description = description;
    }

    /**
     * Updates the start time
     */
    public void setStartTime(String start) {
        this.information.setStartTime(start);
    }

    /**
     * Returns the start time
     */
    public Time getStartTime() {
        return this.information.getStartTime();
    }

    /**
     * Updates the end time
     */
    public void setEndTime(String end) {
        this.information.setEndTime(end);
    }

    /**
     * Returns the end time
     */
    public Time getEndTime() {
        return this.information.getEndTime();
    }

    /**
     * Updates the text
     */
    public void setText(String text) {
        this.information.setText(text);
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
