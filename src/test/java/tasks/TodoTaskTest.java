package tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTaskTest {
    @Test
    public void todoFormatStringTest() {
        TaskInformation info = new TaskInformation("todo read book");
        TodoTask todo = new TodoTask(info);
        String expected = "[T][ ] read book";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void todoFormatStorageTest() {
        TodoTask todo = new TodoTask("read book", true);
        String expected = "T | X | read book";
        String actual = todo.toSave();
        assertEquals(expected, actual);
    }

    @Test
    public void markDoneTest() {
        TaskInformation info = new TaskInformation("todo draw");
        TodoTask todo = new TodoTask(info);
        todo.markDone();
        String expected = "[T][X] draw";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void markUndoneTest() {
        TodoTask todo = new TodoTask("draw", true);
        todo.markUndone();
        String expected = "[T][ ] draw";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }
}
