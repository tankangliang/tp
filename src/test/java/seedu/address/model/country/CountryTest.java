package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.Note;

public class CountryTest {

    private static final String[] COUNTRY_CODES = Locale.getISOCountries();

    @Test
    public void getCountryNotes_addValidNote_throwsUnsupportedOperationException() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            Set<Note> countryNotes = country.getCountryNotes();
            NoteStub genericNote = new NoteStub();
            assertThrows(UnsupportedOperationException.class, () -> countryNotes.add(genericNote));
        }
    }

    @Test
    public void getCountryNotes_deleteNote_throwsUnsupportedOperationException() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            NoteStub genericNote = new NoteStub();
            country.addCountryNote(genericNote);
            Set<Note> countryNotes = country.getCountryNotes();
            assertThrows(UnsupportedOperationException.class, () -> countryNotes.remove(genericNote));
        }
    }

    @Test
    public void addCountryNotes_addValidNote_updatesCountryNotesList() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            assertEquals(0, country.getCountryNotes().size());
            NoteStub genericNote = new NoteStub();
            country.addCountryNote(genericNote);
            assertEquals(1, country.getCountryNotes().size());
        }
    }

    @Test
    public void addCountryNotes_nullNote_throwsNullPointerException() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            assertThrows(NullPointerException.class, () -> {
                NoteStub genericNote = null;
                country.addCountryNote(genericNote);
            });
        }
    }
}
