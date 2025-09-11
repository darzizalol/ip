package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlinesTest {
    /**
     * Testing methods: getStatusText() and toFileFormat()
     */
    @Test
    public void getStatusTextToFileFormat_validInputs() {
        Deadline d = new Deadline("submit report", "02/12/2019 1800", false);

        assertEquals("[D][ ] submit report (by: 02/12/2019 1800)", d.getStatusText());
        assertEquals("D | 0 | submit report | 02/12/2019 1800", d.toFileFormat());
    }
}
