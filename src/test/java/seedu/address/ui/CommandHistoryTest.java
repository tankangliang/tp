package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    @Test
    public void getNext_reachHistoryEnd_throwsNoSuchElementException() {
        CommandHistory commandHistory = CommandHistory.init();
        commandHistory.add("1");
        try {
            commandHistory.getNext();
            fail("NoSuchElementException is expected to be thrown");
        } catch (NoSuchElementException e) {
            assertEquals(1, 1);
        } catch (Exception ex) {
            fail("NoSuchElementException is expected to be thrown");
        }

    }

    @Test
    public void getPrevious_reachHistoryBeginning_throwsNoSuchElementException() {
        CommandHistory commandHistory = CommandHistory.init();
        commandHistory.add("1");
        try {
            commandHistory.getPrevious();
            commandHistory.getPrevious();
            fail("NoSuchElementException is expected to be thrown");
        } catch (NoSuchElementException e) {
            assertEquals(1, 1);
        } catch (Exception ex) {
            fail("NoSuchElementException is expected to be thrown");
        }
    }

    @Test
    public void getPrevious_completeHistory_isMatching() {
        CommandHistory commandHistory = CommandHistory.init();
        commandHistory.add("1");
        commandHistory.add("2");
        commandHistory.add("3");
        assertAll(() -> assertEquals("3", commandHistory.getPrevious()), () ->
                        assertEquals("2", commandHistory.getPrevious()), () ->
                assertEquals("1", commandHistory.getPrevious()));
    }

}
