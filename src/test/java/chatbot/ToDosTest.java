package chatbot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ToDosTest {

    /**
     * Testing methods: getStatusText() and toFileFormat()
     */
    @Test
    public void getStatusText_and_toFileFormat_validInputs() {
        ToDos t1 = new ToDos("read book", false);
        ToDos t2 = new ToDos("read book", true);

        assertEquals("[T][ ] read book", t1.getStatusText());
        assertEquals("[T][X] read book", t2.getStatusText());

        assertEquals("T | 0 | read book", t1.toFileFormat());
        assertEquals("T | 1 | read book", t2.toFileFormat());
    }
}
