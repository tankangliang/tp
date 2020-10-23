package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;

public class CountryNoteViewCommandTest {
    @Test
    public void equals_sameCountry_returnsTrue() {
        Country country = new Country("SG");
        CountryNoteViewCommand first = new CountryNoteViewCommand(country);
        CountryNoteViewCommand second = new CountryNoteViewCommand(country);
        assertEquals(first, second);
    }

    @Test
    public void equals_diffCountry_returnsFalse() {
        Country country = new Country("SG");
        Country country2 = new Country("MY");
        CountryNoteViewCommand first = new CountryNoteViewCommand(country);
        CountryNoteViewCommand second = new CountryNoteViewCommand(country2);
        assertNotEquals(first, second);
    }

}
