package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {

    /**
     * Testing methods: getStatusText() and toFileFormat()
     */
    @Test
    public void getStatusTextToFileFormat_validInputs() {
        ToDo t1 = new ToDo("read book", false);
        ToDo t2 = new ToDo("read book", true);

        assertEquals("[T][ ] read book", t1.getStatusText());
        assertEquals("[T][X] read book", t2.getStatusText());

        assertEquals("T | 0 | read book", t1.toFileFormat());
        assertEquals("T | 1 | read book", t2.toFileFormat());
    }
}
