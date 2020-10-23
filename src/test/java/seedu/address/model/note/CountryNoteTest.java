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

}
