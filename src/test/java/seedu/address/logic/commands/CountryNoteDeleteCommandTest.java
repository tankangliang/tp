package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

public class CountryNoteDeleteCommandTest {
    private Model model = new ModelManager(getTypicalTbmManager(), new UserPrefs());
    private final CountryNote genericCountryNote = new CountryNote("generic note", new Country("SG"));

    @Test
    public void equals_sameObj_returnTrue() {
        CountryNoteDeleteCommand countryNoteDeleteCommand = new CountryNoteDeleteCommand(Index.fromOneBased(1));
        assertTrue(countryNoteDeleteCommand.equals(countryNoteDeleteCommand));
    }

    @Test
    public void equals_null_returnFalse() {
        CountryNoteDeleteCommand countryNoteDeleteCommand = new CountryNoteDeleteCommand(Index.fromOneBased(1));
        assertFalse(countryNoteDeleteCommand.equals(null));
    }

    @Test
    public void equals_sameIndex_returnsTrue() {
        Index index = Index.fromOneBased(1);
        CountryNoteDeleteCommand first = new CountryNoteDeleteCommand(index);
        CountryNoteDeleteCommand second = new CountryNoteDeleteCommand(index);
        assertEquals(first, second);
    }

    @Test
    public void equals_diffIndex_returnsFalse() {
        Index index = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);
        CountryNoteDeleteCommand first = new CountryNoteDeleteCommand(index);
        CountryNoteDeleteCommand second = new CountryNoteDeleteCommand(index2);
        assertNotEquals(first, second);
    }

    @Test
    public void execute_invalidCountryNoteIndex_throwsCommandException() {
        Index invalidCountryNoteIndex = Index.fromOneBased(model.getFilteredCountryNoteList().size() + 1);
        CountryNoteDeleteCommand countryNoteDeleteCommand =
                new CountryNoteDeleteCommand(invalidCountryNoteIndex);
        assertThrows(CommandException.class, () -> countryNoteDeleteCommand.execute(model));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        CountryNoteDeleteCommand countryNoteDeleteCommand = new CountryNoteDeleteCommand(Index.fromOneBased(1));
        assertThrows(NullPointerException.class, () -> countryNoteDeleteCommand.execute(null));
    }

    @Test
    public void execute_validCountryNoteIndex_successfullyDeletesNote() {
        Model model = new ModelManager();
        model.addCountryNote(genericCountryNote);
        Index index = Index.fromOneBased(1);
        CountryNoteDeleteCommand countryNoteDeleteCommand = new CountryNoteDeleteCommand(index);

        Model expectedModel = new ModelManager();
        String expectedMessage = String.format(CountryNoteDeleteCommand.MESSAGE_SUCCESS, index.getOneBased(),
                genericCountryNote);

        assertCommandSuccess(countryNoteDeleteCommand, model, expectedMessage, expectedModel);
    }
}
