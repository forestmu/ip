public class Deadline extends Task {

    protected Time by;

    public Deadline(String description, boolean isDone, Time by) {
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