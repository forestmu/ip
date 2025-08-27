package tasks;

import time.Time;

/**
 * Represents a task with as an event.
 * Extends Task with a start time and end time
 */
public class EventTask extends Task {

    protected Time from;
    protected Time to;

    /**
     * Constructor of Event
     *
     * @param information TaskInformation object holding information of task
     */
    public EventTask(TaskInformation information) {
        super(information);
        this.from = information.getStart();
        this.to = information.getEnd();
    }

    /**
     * Constructor of Event
     *
     * @param description String description of the task
     * @param from the start time of Event
     * @param to  the end time of Event
     * @param isDone  true if task is completed. False otherwise
     */
    public EventTask(String description, boolean isDone, Time from, Time to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string of the task
     * Used for printing out task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.toPrint() + " to:" + to.toPrint() + ")";
    }

    /**
     * Returns string of task
     * Used for printing task in storage
     */
    @Override
    public String toSave() {
        return "E | " + super.toSave() + " | " + from.toString() + " | " + to.toString();
    }
}
