package tasks;

import exceptions.InvalidInputException;
import exceptions.NoEndException;
import exceptions.NoStartException;
import exceptions.NoTaskException;
import time.Time;

/**
 * Represents a set of information for a task
 */
public class TaskInformation {
    private String text;
    private String description;
    private Time startTime;
    private Time endTime;
    private String type;

    /**
     * Constructor of TaskInformation
     *
     * @param text string description of the whole command
     * @param type Type of the
     */
    public TaskInformation(String text, String type) {
        this.text = text;
        this.type = type;
    }

    /**
     * Returns string description of task
     */
    public String getDescription() {
        if (this.type.equals("todo")) {
            //todo has 4 letters hence description
            //starts from index 4
            description = text.substring(4);
        } else if (this.type.equals("deadline")) {
            //description is after 'deadline' and before end time
            int index = text.indexOf("/");
            if (index == -1) {
                throw new InvalidInputException();
            }
            //deadline has 8 letters
            description = text.substring(8, index - 1);
        } else if (this.type.equals("event")) {
            //description is after 'event' and before start time
            int getFrom = text.indexOf("/");
            if (getFrom == -1) {
                throw new InvalidInputException();
            }
            //event has 5 letters
            description = text.substring(5, getFrom - 1);
        } else {
            //should not reach here
            description = "";
        }

        if (description.isBlank()) {
            throw new NoTaskException();
        } else {
            return description.trim();
        }
    }

    /**
     * Returns the start time of task in Time
     */
    public Time getStart() {
        //only event task have start time
        if (!text.startsWith("event")) {
            return null;
        }

        String start;
        int getFrom = text.indexOf("/");
        int getTo = text.lastIndexOf("/");
        if (getFrom == getTo || getFrom == -1 || getTo == -1) {
            throw new InvalidInputException();
        }

        //start time specified after '/from'
        //getTo is the index of '/' hence end of start time
        //is one index before getTo
        start = text.substring(getFrom + 5, getTo - 1);
        if (start.isBlank()) {
            throw new NoStartException();
        }

        startTime = new Time(start.trim());
        return startTime;
    }

    /**
     * Returns end time of task in Time
     */
    public Time getEnd() {
        String end;
        if (text.startsWith("deadline")) {
            int index = text.indexOf("/");
            if (index == -1) {
                throw new InvalidInputException();
            }
            //end time specified after '/by'
            end = text.substring(index + 3);
            if (end.isBlank()) {
                throw new NoEndException();
            }
        } else if (text.startsWith("event")) {
            int index = text.lastIndexOf("/");
            if (index == -1) {
                throw new InvalidInputException();
            }
            //end time specified after '/to'
            end = text.substring(index + 3);
            if (end.isBlank()) {
                throw new NoEndException();
            }
        } else {
            return null;
        }

        endTime = new Time(end.trim());
        return endTime;
    }
}
