package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.note.CountryNote;

public class CountryNotesManagerTest {

    private CountryNotesManager countryNotesManager;

    @BeforeEach
    public void setUp() {
        countryNotesManager = new CountryNotesManager();
    }

    @Test
    public void hasCountryNote() {
        // EP: duplicate note -> true
        Country country = new Country("SG");
        CountryNote genericNote = new CountryNote("generic note", country);
        countryNotesManager.addCountryNote(genericNote);
        assertTrue(countryNotesManager.hasCountryNote(genericNote));

        // EP: non-duplicate note -> false
        country = new Country("MY");
        genericNote = new CountryNote("generic note", country);
        assertFalse(countryNotesManager.hasCountryNote(genericNote));
    }

    @Test
    public void addCountryNote() {
        // EP: add valid note -> countryNotesManager should contain valid note
        Country country = new Country("SG");
        final CountryNote genericNote = new CountryNote("generic note", country);
        assertFalse(countryNotesManager.hasCountryNote(genericNote));
        countryNotesManager.addCountryNote(genericNote);
        assertTrue(countryNotesManager.hasCountryNote(genericNote));

        // EP: add duplicate note -> duplicate note not added
        assertEquals(1, countryNotesManager.asUnmodifiableObservableList()
                .stream()
                .filter(x -> x.equals(genericNote))
                .count());
        countryNotesManager.addCountryNote(genericNote);
        assertEquals(1, countryNotesManager.asUnmodifiableObservableList()
                .stream()
                .filter(x -> x.equals(genericNote))
                .count());

        // EP: add invalid note -> countryNotesManager should not contain invalid note
        CountryNote invalidNote = new CountryNote("generic note", Country.NULL_COUNTRY);
        assertThrows(AssertionError.class, () -> countryNotesManager.addCountryNote(invalidNote));
    }

    @Test
    public void deleteCountryNote() {
        // EP: delete countryNote with null country -> assertion error
        CountryNote countryNote1 = new CountryNote("country note 1", Country.NULL_COUNTRY);
        assertThrows(AssertionError.class, () -> countryNotesManager.deleteCountryNote(countryNote1));

        // EP: delete non-existing country note -> assertion error
        CountryNote countryNote2 = new CountryNote("random", new Country("SG"));
        assertThrows(AssertionError.class, () -> countryNotesManager.deleteCountryNote(countryNote2));

        // EP: null country note -> null ptr error
        assertThrows(NullPointerException.class, () -> countryNotesManager.deleteCountryNote(null));

        // EP: delete existing country note -> true
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        countryNotesManager.addCountryNote(countryNote);
        assertTrue(countryNotesManager.hasCountryNote(countryNote));
        countryNotesManager.deleteCountryNote(countryNote);
        assertFalse(countryNotesManager.hasCountryNote(countryNote));
    }

    @Test
    public void asUnmodifiableObservableList_editList_throwsUnsupportedOperationException() {
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        assertThrows(UnsupportedOperationException.class, () ->
                countryNotesManager.asUnmodifiableObservableList().add(countryNote));
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(countryNotesManager);

        // same clients and country notes -> returns true
        assertTrue(countryNotesManager.equals(new CountryNotesManager()));

        // different country notes -> returns false
        CountryNotesManager countryNotesManagerWithCountryNote = new CountryNotesManager();
        countryNotesManagerWithCountryNote.addCountryNote(new CountryNote("country note", new Country("AL")));
        assertFalse(countryNotesManager.equals(countryNotesManagerWithCountryNote));
    }

    @Test
    public void hashCode_test() {
        // same object -> same hashcode
        assertEquals(countryNotesManager.hashCode(), countryNotesManager.hashCode());

        // same clients and country notes -> same hashcode
        assertEquals(countryNotesManager.hashCode(), new CountryNotesManager().hashCode());

        // different country notes -> different hashcode
        CountryNotesManager countryNotesManagerWithCountryNote = new CountryNotesManager();
        countryNotesManagerWithCountryNote.addCountryNote(new CountryNote("country note", new Country("AL")));
        assertNotEquals(countryNotesManager.hashCode(), countryNotesManagerWithCountryNote.hashCode());
    }

    @Test
    public void setCountryNote() {
        // EP: valid old and new country note -> replace old with new
        CountryNote oldCountryNote = new CountryNote("random", new Country("SG"));
        CountryNote newCountryNote = new CountryNote("random2", new Country("MY"));

        countryNotesManager.addCountryNote(oldCountryNote);
        assertTrue(countryNotesManager.hasCountryNote(oldCountryNote));
        assertFalse(countryNotesManager.hasCountryNote(newCountryNote));

        countryNotesManager.setCountryNote(oldCountryNote, newCountryNote);
        assertFalse(countryNotesManager.hasCountryNote(oldCountryNote));
        assertTrue(countryNotesManager.hasCountryNote(newCountryNote));

        // EP: non-existing old country note -> assertion error
        CountryNote oldCountryNote1 = new CountryNote("random3", new Country("SG"));
        CountryNote newCountryNote1 = new CountryNote("random4", new Country("MY"));
        assertThrows(AssertionError.class, () ->
                countryNotesManager.setCountryNote(oldCountryNote1, newCountryNote1));
    }

}
