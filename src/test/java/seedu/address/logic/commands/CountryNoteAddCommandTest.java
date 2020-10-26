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

public class CountryNoteAddCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_duplicateCountryNote_throwsCommandException() {
        Country country = new Country("SG");
        CountryNote genericNote = new CountryNote("generic note", country);
        model.addCountryNote(genericNote);
        CountryNoteAddCommand countryNoteAddCommand = new CountryNoteAddCommand(genericNote);
        assertThrows(CommandException.class, () -> countryNoteAddCommand.execute(model));
    }

    @Test
    public void execute_notDuplicateCountryNote_successfullyAddsCountryNote() {
        try {
            Country country = new Country("SG");
            CountryNote genericNote = new CountryNote("generic note", country);
            assertFalse(model.hasCountryNote(genericNote));
            CountryNoteAddCommand countryNoteAddCommand = new CountryNoteAddCommand(genericNote);
            countryNoteAddCommand.execute(model);
            assertTrue(model.hasCountryNote(genericNote));
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void equals_sameObj_returnTrue() {
        CountryNoteAddCommand countryNoteAddCommand = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        assertTrue(countryNoteAddCommand.equals(countryNoteAddCommand));
    }

    @Test
    public void equals_notCountryNoteCommand_returnFalse() {
        CountryNoteAddCommand countryNoteAddCommand = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        Object obj = new Object();
        assertFalse(countryNoteAddCommand.equals(obj));
    }

    @Test
    public void equals_diffCountryDiffNote_returnFalse() {
        CountryNoteAddCommand countryNoteAddCommandFirst = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteAddCommand countryNoteAddCommandSecond = new CountryNoteAddCommand(
                new CountryNote("generic2", new Country("MY")));
        assertFalse(countryNoteAddCommandFirst.equals(countryNoteAddCommandSecond));
        assertFalse(countryNoteAddCommandSecond.equals(countryNoteAddCommandFirst));
    }

    @Test
    public void equals_diffCountrySameNote_returnFalse() {
        CountryNoteAddCommand countryNoteAddCommandFirst = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteAddCommand countryNoteAddCommandSecond = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("MY")));
        assertFalse(countryNoteAddCommandFirst.equals(countryNoteAddCommandSecond));
        assertFalse(countryNoteAddCommandSecond.equals(countryNoteAddCommandFirst));
    }

    @Test
    public void equals_sameCountryDiffNote_returnFalse() {
        CountryNoteAddCommand countryNoteAddCommandFirst = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteAddCommand countryNoteAddCommandSecond = new CountryNoteAddCommand(
                new CountryNote("generic2", new Country("SG")));
        assertFalse(countryNoteAddCommandFirst.equals(countryNoteAddCommandSecond));
        assertFalse(countryNoteAddCommandSecond.equals(countryNoteAddCommandFirst));
    }

    @Test
    public void equals_sameCountrySameNote_returnTrue() {
        CountryNoteAddCommand countryNoteAddCommandFirst = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        CountryNoteAddCommand countryNoteAddCommandSecond = new CountryNoteAddCommand(
                new CountryNote("generic", new Country("SG")));
        assertTrue(countryNoteAddCommandFirst.equals(countryNoteAddCommandSecond));
        assertTrue(countryNoteAddCommandSecond.equals(countryNoteAddCommandFirst));
    }
}
