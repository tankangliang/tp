package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;
import seedu.address.ui.WidgetViewOption;

public class CommandResultTest {

    @Test
    public void constructor() {
        CommandResult defaultCommandResult = new CommandResult("test");
        assertFalse(defaultCommandResult.isShowHelp());
        assertFalse(defaultCommandResult.isExit());
        assertEquals("NONE", defaultCommandResult.getWidgetViewOptionAsString());
        assertFalse(defaultCommandResult.shouldDisplayClient());
        assertFalse(defaultCommandResult.shouldDisplayCountryNote());

        CommandResult customCommandResult = new CommandResult("test", true, false,
                WidgetViewOption.generateClientWidgetOption());
        assertTrue(customCommandResult.isShowHelp());
        assertFalse(customCommandResult.isExit());
        assertEquals("CLIENT", customCommandResult.getWidgetViewOptionAsString());
        assertEquals(customCommandResult.getFeedbackToUser(), "test");
        assertTrue(customCommandResult.shouldDisplayClient());
        assertFalse(customCommandResult.shouldDisplayCountryNote());

        customCommandResult = new CommandResult("test", true, false,
                WidgetViewOption.generateCountryNoteWidgetOption(Country.NULL_COUNTRY));
        assertEquals("COUNTRY_NOTE", customCommandResult.getWidgetViewOptionAsString());
        assertFalse(customCommandResult.shouldDisplayClient());
        assertTrue(customCommandResult.shouldDisplayCountryNote());
    }

    @Test
    public void getCountry_nullCountry_returnExpected() {
        CommandResult commandResult = new CommandResult("test", false, false,
                WidgetViewOption.generateCountryNoteWidgetOption(Country.NULL_COUNTRY));
        assertEquals(Country.NULL_COUNTRY, commandResult.getCountry());
    }

    @Test
    public void getCountry_validCountry_returnExpected() {
        CommandResult commandResult = new CommandResult("test", false, false,
                WidgetViewOption.generateCountryNoteWidgetOption(new Country("SG")));
        assertEquals(new Country("SG"), commandResult.getCountry());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // basic equals tests
        basicEqualsTests(commandResult);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashCode_test() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

}
