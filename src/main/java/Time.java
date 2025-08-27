import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime dateTime;

    public Time(String time) {
        this.dateTime = LocalDateTime.parse(time, FORMAT);
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    @Override
    public String toString() {
        return dateTime.format(FORMAT);
    }
}
