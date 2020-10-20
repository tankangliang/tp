package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.CountryNote;

public class CountryManagerTest {

    //TODO: Add more tests if decide to include checking for 3-letter Country Code
    private static final String[] COUNTRY_CODES = Locale.getISOCountries();

    @Test
    public void isValidCode_validCode_returnTrue() {
        for (String countryCode : COUNTRY_CODES) {
            assertTrue(CountryManager.isValidCountryCode(countryCode));
        }
    }

    @Test
    public void isValidCode_invalidCode_returnFalse() {
        assertFalse(CountryManager.isValidCountryCode("ZZ"));
        assertFalse(CountryManager.isValidCountryCode("12"));
        assertFalse(CountryManager.isValidCountryCode("az"));
        assertFalse(CountryManager.isValidCountryCode("bd"));
        assertFalse(CountryManager.isValidCountryCode("bdc"));
    }

    @Test
    public void hasCountryNote_duplicateNote_returnTrue() {
        CountryManager countryManager = new CountryManager();
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            countryManager.addCountryNote(genericNote);
            assertTrue(countryManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void hasCountryNote_notDuplicateNote_returnFalse() {
        CountryManager countryManager = new CountryManager();
        for (String countryCode : COUNTRY_CODES) {
            CountryNote genericNote = new CountryNote("generic note", new Country(countryCode));
            assertFalse(countryManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void addCountryNote_validNote_updatesCorrectly() {
        CountryManager countryManager = new CountryManager();
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            assertFalse(countryManager.hasCountryNote(genericNote));
            countryManager.addCountryNote(genericNote);
            assertTrue(countryManager.hasCountryNote(genericNote));
        }
    }

}
