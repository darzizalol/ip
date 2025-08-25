package chatbot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    // Testing methods: getStatusText() and toFileFormat()
    public void getStatusText_and_toFileFormat_validInputs() {
        Deadlines d = new Deadlines("submit report", "02/12/2019 1800", false);

        assertEquals("[D][ ] submit report (by: 02/12/2019 1800)", d.getStatusText());
        assertEquals("D | 0 | submit report | 02/12/2019 1800", d.toFileFormat());
    }
}
