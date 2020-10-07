package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.Note;

public class CountryTest {
    @Test
    public void addCountryNotes_addValidNote_updatesCountryNotesList() {
        assertEquals(0, Country.SINGAPORE.getCountryNotes().size());
        NoteStub genericNote = new NoteStub();
        Country.SINGAPORE.addCountryNote(genericNote);
        assertEquals(1, Country.SINGAPORE.getCountryNotes().size());
    }

    @Test
    public void addCountryNotes_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            NoteStub genericNote = null;
            Country.SINGAPORE.addCountryNote(genericNote);
        });
    }

    /**
     * A stub of Note which is an empty class
     */
    private static class NoteStub extends Note {
        public NoteStub() {
        }
    }
}
