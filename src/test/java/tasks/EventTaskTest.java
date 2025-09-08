package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import time.Time;

public class EventTaskTest {
    @Test
    public void eventFormatStringTest() {
        TaskInformation info = new TaskInformation("event read book /from 02-06-2025 1800 /to 12-06-2025 1800",
                "event");
        EventTask event = new EventTask(info);
        String expected = "[E][ ] read book (from:2 Jun 2025 18:00 to:12 Jun 2025 18:00)";
        String actual = event.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void eventFormatStorageTest() {
        TaskInformation info = new TaskInformation("event read book /from 02-06-2025 1800 /to 12-06-2025 1800",
                "event");
        EventTask event = new EventTask(info);
        String expected = "E |   | read book | 02-06-2025 1800 | 12-06-2025 1800";
        String actual = event.toSave();
        assertEquals(expected, actual);
    }
}
