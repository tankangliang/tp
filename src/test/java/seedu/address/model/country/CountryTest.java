package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.Note;

public class CountryTest {

    private static final String[] countryCodes = Locale.getISOCountries();

    @Test
    public void getCountryNotes_addValidNote_throwsUnsupportedOperationException() {
        for (String countryCode : countryCodes) {
            Country country = new Country(countryCode);
            List<Note> countryNotes = country.getCountryNotes();
            NoteStub genericNote = new NoteStub();
            assertThrows(UnsupportedOperationException.class, () -> countryNotes.add(genericNote));
        }
    }

    @Test
    public void getCountryNotes_deleteNote_throwsUnsupportedOperationException() {
        for (String countryCode : countryCodes) {
            Country country = new Country(countryCode);
            NoteStub genericNote = new NoteStub();
            country.addCountryNote(genericNote);
            List<Note> countryNotes = country.getCountryNotes();
            assertThrows(UnsupportedOperationException.class, () -> countryNotes.remove(0));
        }
    }

    @Test
    public void addCountryNotes_addValidNote_updatesCountryNotesList() {
        for (String countryCode : countryCodes) {
            Country country = new Country(countryCode);
            assertEquals(0, country.getCountryNotes().size());
            NoteStub genericNote = new NoteStub();
            country.addCountryNote(genericNote);
            assertEquals(1, country.getCountryNotes().size());
        }
    }

    @Test
    public void addCountryNotes_nullNote_throwsNullPointerException() {
        for (String countryCode : countryCodes) {
            Country country = new Country(countryCode);
            assertThrows(NullPointerException.class, () -> {
                NoteStub genericNote = null;
                country.addCountryNote(genericNote);
            });
        }
    }

    /**
     * A stub of Note which is an empty class
     */
    private static class NoteStub extends Note {

    }
}
