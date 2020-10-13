package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class CountryTest {

    private static final String[] COUNTRY_CODES = Locale.getISOCountries();

    @Test
    public void equals_notACountry_returnFalse() {
        for (String countryCode : COUNTRY_CODES) {
            Country countryFirst = new Country(countryCode);
            Object obj = new Object();
            assertNotEquals(countryFirst, obj);
        }
    }

    @Test
    public void equals_diffCountry_returnFalse() {
        for (int i = 0; i < COUNTRY_CODES.length - 1; i++) {
            Country countryFirst = new Country(COUNTRY_CODES[i]);
            Country countrySecond = new Country(COUNTRY_CODES[i + 1]);
            assertNotEquals(countryFirst, countrySecond);
            assertNotEquals(countrySecond, countryFirst);
        }
    }

    @Test
    public void equals_sameCountry_returnTrue() {
        for (String countryCode : COUNTRY_CODES) {
            Country countryFirst = new Country(countryCode);
            Country countrySecond = new Country(countryCode);
            assertEquals(countryFirst, countrySecond);
            assertEquals(countrySecond, countryFirst);
        }
    }
}
