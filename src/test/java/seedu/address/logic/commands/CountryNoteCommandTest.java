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
import seedu.address.model.country.NoteStub;

public class CountryNoteCommandTest {
    //TODO: Add more fine-grained tests

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_duplicateCountryNote_throwsCommandException() {
        Country country = new Country("SG");
        NoteStub genericNote = new NoteStub("generic note");
        model.addCountryNote(country, genericNote);
        CountryNoteCommand countryNoteCommand = new CountryNoteCommand(country, genericNote);
        assertThrows(CommandException.class, () -> countryNoteCommand.execute(model));
    }

    @Test
    public void execute_notDuplicateCountryNote_successfullyAddsCountryNote() {
        try {
            Country country = new Country("SG");
            NoteStub genericNote = new NoteStub("generic note");
            assertFalse(model.hasCountryNote(country, genericNote));
            CountryNoteCommand countryNoteCommand = new CountryNoteCommand(country, genericNote);
            countryNoteCommand.execute(model);
            assertTrue(model.hasCountryNote(country, genericNote));
        } catch (CommandException e) {
            fail();
        }

    }
}
