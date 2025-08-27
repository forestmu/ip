package time;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    @Test
    public void timeStringTest() {
        Time time = new Time("10-12-2022 1800");
        String expected = "10-12-2022 1800";
        String actual = time.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void timePrintTest() {
        Time time = new Time("10-12-2022 1800");
        String expected = "10 Dec 2022 18:00";
        String actual = time.toPrint();
        assertEquals(expected, actual);
    }
}
