package tasks;

import candy.Parser;
import candy.Ui;
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
     * Checks if the index if valid (existing)
     *
     * @param index the index to check
     */
    public void checkIndex(int index) {
        if (index == -1) {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns string description of task
     */
    public String getDescription() {
        //Used chatGPT to search and understand the indexOf function
        if (this.type.equals("todo")) {
            //todo has 4 letters hence description
            //starts from index 4
            description = text.substring(4);
        } else if (this.type.equals("deadline")) {
            //description is after 'deadline' and before end time
            int index = text.indexOf("/");
            checkIndex(index);
            //deadline has 8 letters
            description = text.substring(8, index - 1);
        } else if (this.type.equals("event")) {
            //description is after 'event' and before start time
            int getFrom = text.indexOf("/");
            checkIndex(getFrom);
            //event has 5 letters
            description = text.substring(5, getFrom - 1);
        } else {
            //should not reach here
            description = "";
        }

        if (description.isBlank()) {
            throw new NoTaskException();
        }
        return description.trim();
    }

    /**
     * Returns the start time of task in String
     */
    public String getStartString() {
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
        return start.trim();
    }

    /**
     * Returns the start time of task in Time
     */
    public Time getStartTime() {
        String start = getStartString();
        if (start == null) {
            return null;
        }
        startTime = new Time(start);
        return startTime;
    }

    /**
     * Returns end time of task in String
     */
    public String getEndString() {
        String end;
        if (text.startsWith("deadline")) {
            int index = text.indexOf("/");
            checkIndex(index);
            //end time specified after '/by'
            end = text.substring(index + 3);
            if (end.isBlank()) {
                throw new NoEndException();
            }
        } else if (text.startsWith("event")) {
            int index = text.lastIndexOf("/");
            checkIndex(index);
            //end time specified after '/to'
            end = text.substring(index + 3);
            if (end.isBlank()) {
                throw new NoEndException();
            }
        } else {
            return null;
        }
        return end.trim();
    }

    /**
     * Returns end time of task in Time
     */
    public Time getEndTime() {
        String end = getEndString();
        if (end == null) {
            return null;
        }
        endTime = new Time(end);
        return endTime;
    }

    public String getType() {
        return this.type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(String start) {
        this.startTime = new Time(start);
    }

    public void setEndTime(String end) {
        this.endTime = new Time(end);
    }

    public void setText(String text) {
        this.text = this.type + " " + text;
    }

}
