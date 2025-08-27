package time;

import exceptions.InvalidTimeInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HHmm");
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern(
            "d MMM yyyy HH:mm");
    private LocalDateTime dateTime;

    public Time(String time) {
        try {
            this.dateTime = LocalDateTime.parse(time, FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeInputException();
        }
    }

    @Override
    public String toString() {
        return dateTime.format(FORMAT);
    }

    public String toPrint() {
        return dateTime.format(PRINTFORMAT);
    }
}
