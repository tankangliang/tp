package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.tag.Tag;

public class CountryNoteEditCommandTest {
    @Test
    public void equals_allEqual_equal() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(Index.fromOneBased(1),
                new CountryNote("abc", Country.NULL_COUNTRY));
        CountryNoteEditCommand actual = new CountryNoteEditCommand(Index.fromOneBased(1),
                new CountryNote("abc", Country.NULL_COUNTRY));
        assertEquals(expected, actual);
    }

    @Test
    public void equals_diffIndex_notEqual() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(Index.fromOneBased(1),
                new CountryNote("abc", Country.NULL_COUNTRY));
        CountryNoteEditCommand actual = new CountryNoteEditCommand(Index.fromOneBased(2),
                new CountryNote("abc", Country.NULL_COUNTRY));
        assertNotEquals(expected, actual);
    }

    @Test
    public void equals_diffCountryNote_notEqual() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(Index.fromOneBased(1),
                new CountryNote("abcd", Country.NULL_COUNTRY));
        CountryNoteEditCommand actual = new CountryNoteEditCommand(Index.fromOneBased(1),
                new CountryNote("abc", Country.NULL_COUNTRY));
        assertNotEquals(expected, actual);
    }

    @Test
    public void equals_sameTags_equal() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(Index.fromOneBased(1), new HashSet<>());
        CountryNoteEditCommand actual = new CountryNoteEditCommand(Index.fromOneBased(1), new HashSet<>());
        assertEquals(expected, actual);
    }

    @Test
    public void equals_diffTags_equal() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("a"));
        CountryNoteEditCommand expected = new CountryNoteEditCommand(Index.fromOneBased(1), tags);
        CountryNoteEditCommand actual = new CountryNoteEditCommand(Index.fromOneBased(1), new HashSet<>());
        assertNotEquals(expected, actual);
    }
}
