package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

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
            NoteStub genericNote = new NoteStub("generic note");
            countryManager.addCountryNote(country, genericNote);
            assertTrue(countryManager.hasCountryNote(country, genericNote));
        }
    }

    @Test
    public void hasCountryNote_notDuplicateNote_returnFalse() {
        CountryManager countryManager = new CountryManager();
        for (String countryCode : COUNTRY_CODES) {
            NoteStub genericNote = new NoteStub("generic note");
            assertFalse(countryManager.hasCountryNote(new Country(countryCode), genericNote));
        }
    }

    @Test
    public void addCountryNote_validNote_updatesCorrectly() {
        CountryManager countryManager = new CountryManager();
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            NoteStub genericNote = new NoteStub("generic note");
            assertFalse(countryManager.hasCountryNote(country, genericNote));
            countryManager.addCountryNote(new Country(countryCode), genericNote);
            assertTrue(countryManager.hasCountryNote(country, genericNote));
        }
    }

}
