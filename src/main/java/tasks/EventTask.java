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
     * Constructor of an event task
     *
     * @param information TaskInformation object holding information of task
     */
    public EventTask(TaskInformation information) {
        super(information);
        this.from = information.getStartTime();
        this.to = information.getEndTime();
    }

//    /**
//     * Constructor of an event task
//     *
//     * @param description String description of the task
//     * @param from the start time of Event
//     * @param to  the end time of Event
//     * @param isDone  true if task is completed. False otherwise
//     */
//    public EventTask(String description, boolean isDone, Time from, Time to) {
//        super(description, isDone);
//        this.from = from;
//        this.to = to;
//    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.toPrint()
                + " to:" + to.toPrint() + ")";
    }

    @Override
    public String toSave() {
        return "E | " + super.toSave() + " | " + from.toString()
                + " | " + to.toString();
    }

    @Override
    public void setStartTime(String end) {
        super.setStartTime(end);
        this.from = super.getStartTime();
    }

    @Override
    public void setEndTime(String end) {
        super.setEndTime(end);
        this.to = super.getEndTime();
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
