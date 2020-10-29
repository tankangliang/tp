package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;
import seedu.address.model.tag.Tag;

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
    public void constructor_countryNote_ensureEqualsExpected() {
        String content = "content";
        Country country = new Country("SG");
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("t"));
        CountryNote c = new CountryNote(content, country, tags);
        assertEquals(content, c.getNoteContent());
        assertEquals(country, c.getCountry());
        assertEquals(tags, c.getTags());
    }

}
