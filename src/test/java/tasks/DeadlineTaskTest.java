package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import time.Time;

public class DeadlineTaskTest {
    @Test
    public void deadlineFormatStringTest() {
        TaskInformation info = new TaskInformation("deadline read book /by 02-06-2025 1800", "deadline");
        DeadlineTask deadline = new DeadlineTask(info);
        String expected = "[D][ ] read book (by:2 Jun 2025 18:00)";
        String actual = deadline.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deadlineFormatStorageTest() {
        TaskInformation info = new TaskInformation("deadline read book /by 02-06-2025 1800", "deadline");
        DeadlineTask deadline = new DeadlineTask(info);
        String expected = "D |   | read book | 02-06-2025 1800";
        String actual = deadline.toSave();
        assertEquals(expected, actual);
    }
}
