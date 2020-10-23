package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.CountryNote;

public class CountryNotesManagerTest {

    //TODO: Add more tests if decide to include checking for 3-letter Country Code
    private static final String[] COUNTRY_CODES = Locale.getISOCountries();

    @Test
    public void hasCountryNote_duplicateNote_returnTrue() {
        CountryNotesManager countryNotesManager = new CountryNotesManager();
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            countryNotesManager.addCountryNote(genericNote);
            assertTrue(countryNotesManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void hasCountryNote_notDuplicateNote_returnFalse() {
        CountryNotesManager countryNotesManager = new CountryNotesManager();
        for (String countryCode : COUNTRY_CODES) {
            CountryNote genericNote = new CountryNote("generic note", new Country(countryCode));
            assertFalse(countryNotesManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void addCountryNote_validNote_updatesCorrectly() {
        CountryNotesManager countryNotesManager = new CountryNotesManager();
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            assertFalse(countryNotesManager.hasCountryNote(genericNote));
            countryNotesManager.addCountryNote(genericNote);
            assertTrue(countryNotesManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void addCountryNote_duplicateNote_notAdded() {
        CountryNotesManager countryNotesManager = new CountryNotesManager();
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            countryNotesManager.addCountryNote(genericNote);
            assertEquals(1, countryNotesManager.asUnmodifiableObservableList()
                .stream()
                .filter(x -> x.equals(genericNote))
                .count());
            countryNotesManager.addCountryNote(genericNote);
            assertEquals(1, countryNotesManager.asUnmodifiableObservableList()
                .stream()
                .filter(x -> x.equals(genericNote))
                .count());
        }
    }

}
