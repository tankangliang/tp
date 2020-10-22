package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;

public class JsonAdaptedNoteTest {
    @Test
    public void toModelType_validClientNote_returnsClientNote() {
        JsonAdaptedNote jsonAdaptedNote = new JsonAdaptedNote(new Note("some note"));
        assertTrue(jsonAdaptedNote.toModelType().isClientNote());
    }

    @Test
    public void toModelType_validCountryNote_returnsCountryNote() {
        JsonAdaptedNote jsonAdaptedNote = new JsonAdaptedNote(new CountryNote("some note", new Country("SG")));
        assertFalse(jsonAdaptedNote.toModelType().isClientNote());
    }

}
