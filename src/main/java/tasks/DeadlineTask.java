package tasks;

import time.Time;

public class DeadlineTask extends Task {
    protected Time by;

    public DeadlineTask(TaskInformation information) {
        super(information);
        this.by = information.getEnd();
    }

    public DeadlineTask(String description, boolean isDone, Time by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.toPrint() + ")";
    }

    @Override
    public String toSave() {
        return "D | " + super.toSave() + " | " + by.toString();
    }
}