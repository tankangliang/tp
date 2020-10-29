package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;

public class CountryNoteTest {

    @Test
    public void isClientNote_returnFalse() {
        CountryNote countryNote = new CountryNote("some", new Country("SG"));
        assertFalse(countryNote.isClientNote());
    }

    @Test
    public void getCountry() {
        Country country = new Country("SG");
        CountryNote countryNote = new CountryNote("some", country);
        assertEquals(country, countryNote.getCountry());
    }

    @Test
    public void constructor_countryNote_ensureIsEqual() {
        CountryNote c = new CountryNote("random", new Country("MY"));
        CountryNote copy = new CountryNote(c);
        assertEquals(copy, c);
    }

    @Test
    public void setCountry_returnsExpected() {
        CountryNote c = new CountryNote("random", new Country("MY"));
        CountryNote actual = c.setCountry(new Country("SG"));
        CountryNote expected = new CountryNote("random", new Country("SG"));
        assertEquals(expected, actual);
    }

}
