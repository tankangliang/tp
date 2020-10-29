package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;
import seedu.address.model.tag.Tag;

public class CountryNoteTest {

    private static final String COUNTRY_NOTE_CONTENT = "country note";
    private static final Country COUNTRY = new Country("SG");
    private CountryNote COUNTRY_NOTE;

    @BeforeEach
    public void setUp() {
        COUNTRY_NOTE = new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY);
    }

    @Test
    public void constructor() {
        assertThrows(NullPointerException.class, () -> new CountryNote(null, COUNTRY));
        assertThrows(NullPointerException.class, () -> new CountryNote(COUNTRY_NOTE_CONTENT, null));
        assertEquals(COUNTRY, COUNTRY_NOTE.getCountry());
        assertEquals(COUNTRY_NOTE_CONTENT, COUNTRY_NOTE.getNoteContent());
    }

    @Test
    public void setTags() {
        Set<Tag> tags = Set.of(new Tag("tag1"));
        COUNTRY_NOTE.setTags(tags);
        assertEquals(COUNTRY_NOTE.getTags(), tags);
    }

    @Test
    public void isClientNote_returnFalse() {
        assertFalse(COUNTRY_NOTE.isClientNote());
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(COUNTRY_NOTE);

        // same note content same country -> returns true
        assertTrue(COUNTRY_NOTE.equals(new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY)));

        // different country -> returns false
        assertTrue(COUNTRY_NOTE.equals(new CountryNote(COUNTRY_NOTE_CONTENT, new Country("AL"))));

        // different note content -> returns false
        assertTrue(COUNTRY_NOTE.equals(new CountryNote("new country note", COUNTRY)));

        // different tags -> return false
        CountryNote countryNoteWithTags = new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY);
        countryNoteWithTags.setTags(Set.of(new Tag("tag1")));
        assertFalse(COUNTRY_NOTE.equals(countryNoteWithTags));
    }

    @Test
    public void hashCode_test() {
        // same note content same country -> same hashcode
        assertEquals(COUNTRY_NOTE.hashCode(), new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY).hashCode());

        // different country -> different hashcode
        assertNotEquals(COUNTRY_NOTE.hashCode(), new CountryNote(COUNTRY_NOTE_CONTENT, new Country("AL")).hashCode());

        // different note content -> different hashcode
        assertNotEquals(COUNTRY_NOTE.hashCode(), new CountryNote("new country note", COUNTRY).hashCode());

        // different tags -> different hashcode
        CountryNote countryNoteWithTags = new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY);
        countryNoteWithTags.setTags(Set.of(new Tag("tag1")));
        assertNotEquals(COUNTRY_NOTE.hashCode(), countryNoteWithTags.hashCode());
    }

}
