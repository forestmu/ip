package tasks;

import time.Time;

public class EventTask extends Task {

    protected Time from;
    protected Time to;

    public EventTask(TaskInformation information) {
        super(information);
        this.from = information.getStart();
        this.to = information.getEnd();
    }

    public EventTask(String description, boolean isDone, Time from, Time to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.toPrint() + " to:" + to.toPrint() + ")";
    }

    @Override
    public String toSave() {
        return "E | " + super.toSave() + " | " + from.toString() + " | " + to.toString();
    }
}
