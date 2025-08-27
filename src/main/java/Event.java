public class Event extends Task {

    protected Time from;
    protected Time to;

    public Event(String description, boolean isDone, Time from, Time to) {
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
