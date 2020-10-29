package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CountryNoteEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.TagNoteMap;
import seedu.address.model.tag.Tag;

public class CountryNoteEditCommandParserTest {

    private final TagNoteMap tagNoteMap = new TagNoteMap();
    private final CountryNoteEditCommandParser parser = new CountryNoteEditCommandParser(tagNoteMap);

    @Test
    public void parse_WithIndexWithNoteWithTag_returnsExpected() {
        try {
            CountryNote c = new CountryNote("abc", Country.NULL_COUNTRY);
            Set<Tag> tags = new HashSet<>();
            tags.add(new Tag("a"));
            c.setTags(tags);
            CountryNoteEditCommand expected = new CountryNoteEditCommand(Index.fromOneBased(1), c);

            CountryNoteEditCommand actual = parser.parse(" 1 nt/abc t/a");
            assertEquals(expected, actual);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parse_noIndexWithNoteNoTag_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" nt/abc "));
        assertThrows(ParseException.class, () -> parser.parse(" nt/y"));
        assertThrows(ParseException.class, () -> parser.parse(" abc nt/y"));
        assertThrows(ParseException.class, () -> parser.parse(" a 1 a nt/abc"));
    }

    @Test
    public void parse_withIndexNoNoteNoTag_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" 1 "));
        assertThrows(ParseException.class, () -> parser.parse(" 1 t/"));
        assertThrows(ParseException.class, () -> parser.parse(" 1 nt/"));
        assertThrows(ParseException.class, () -> parser.parse(" 1 nt/ t/"));
    }

}
