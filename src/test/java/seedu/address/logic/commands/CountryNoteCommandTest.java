package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

public class CountryNoteCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_duplicateCountryNote_throwsCommandException() {
        Country country = new Country("SG");
        CountryNote genericNote = new CountryNote("generic note", country);
        model.addCountryNote(genericNote);
        CountryNoteCommand countryNoteCommand = new CountryNoteCommand(genericNote);
        assertThrows(CommandException.class, () -> countryNoteCommand.execute(model));
    }

    @Test
    public void execute_notDuplicateCountryNote_successfullyAddsCountryNote() {
        try {
            Country country = new Country("SG");
            CountryNote genericNote = new CountryNote("generic note", country);
            assertFalse(model.hasCountryNote(genericNote));
            CountryNoteCommand countryNoteCommand = new CountryNoteCommand(genericNote);
            countryNoteCommand.execute(model);
            assertTrue(model.hasCountryNote(genericNote));
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void equals_sameObj_returnTrue() {
        CountryNoteCommand countryNoteCommand = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        assertTrue(countryNoteCommand.equals(countryNoteCommand));
    }

    @Test
    public void equals_notCountryNoteCommand_returnFalse() {
        CountryNoteCommand countryNoteCommand = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        Object obj = new Object();
        assertFalse(countryNoteCommand.equals(obj));
    }

    @Test
    public void equals_diffCountryDiffNote_returnFalse() {
        CountryNoteCommand countryNoteCommandFirst = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteCommand countryNoteCommandSecond = new CountryNoteCommand(
                new CountryNote("generic2", new Country("MY")));
        assertFalse(countryNoteCommandFirst.equals(countryNoteCommandSecond));
        assertFalse(countryNoteCommandSecond.equals(countryNoteCommandFirst));
    }

    @Test
    public void equals_diffCountrySameNote_returnFalse() {
        CountryNoteCommand countryNoteCommandFirst = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteCommand countryNoteCommandSecond = new CountryNoteCommand(
                new CountryNote("generic", new Country("MY")));
        assertFalse(countryNoteCommandFirst.equals(countryNoteCommandSecond));
        assertFalse(countryNoteCommandSecond.equals(countryNoteCommandFirst));
    }

    @Test
    public void equals_sameCountryDiffNote_returnFalse() {
        CountryNoteCommand countryNoteCommandFirst = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteCommand countryNoteCommandSecond = new CountryNoteCommand(
                new CountryNote("generic2", new Country("SG")));
        assertFalse(countryNoteCommandFirst.equals(countryNoteCommandSecond));
        assertFalse(countryNoteCommandSecond.equals(countryNoteCommandFirst));
    }

    @Test
    public void equals_sameCountrySameNote_returnTrue() {
        CountryNoteCommand countryNoteCommandFirst = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteCommand countryNoteCommandSecond = new CountryNoteCommand(
                new CountryNote("generic", new Country("SG")));
        assertTrue(countryNoteCommandFirst.equals(countryNoteCommandSecond));
        assertTrue(countryNoteCommandSecond.equals(countryNoteCommandFirst));
    }
}
