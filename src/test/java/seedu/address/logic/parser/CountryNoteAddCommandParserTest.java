package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CountryNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.TagNoteMap;
import seedu.address.model.tag.Tag;

public class CountryNoteAddCommandParserTest {

    private final TagNoteMap tagNoteMap = new TagNoteMap();
    private final CountryNoteAddCommandParser parser = new CountryNoteAddCommandParser(tagNoteMap);

    @Test
    public void parse_noCountryNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" random string"));
    }

    @Test
    public void parse_withPreamable_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" random string c/SG nt/abc t/a"));
        assertThrows(ParseException.class, () -> parser.parse(" random string c/SG nt/ t/"));
        assertThrows(ParseException.class, () -> parser.parse(" random string c/ nt/abc t"));
        assertThrows(ParseException.class, () -> parser.parse(" random string c/ nt/ t//"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/SG nt/abc"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/SG nt/"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/ nt/abc"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/ nt/"));
    }

    @Test
    public void parse_hasCountryNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/SG random string"));
        assertThrows(ParseException.class, () -> parser.parse(" c/SG t/random string"));
        assertThrows(ParseException.class, () -> parser.parse(" c/SG t/"));
    }

    @Test
    public void parse_invalidCountryHasNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/Z Z nt/random string"));
        assertThrows(ParseException.class, () -> parser.parse(" c/Z Z t/a nt/random string"));
        assertThrows(ParseException.class, () -> parser.parse(" c/Z Z t/ nt/"));
    }

    @Test
    public void parse_validCountryNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/MY "));
        assertThrows(ParseException.class, () -> parser.parse(" c/MY t/a"));
        assertThrows(ParseException.class, () -> parser.parse(" c/MY nt/ t/"));
    }

    @Test
    public void parse_validCountryHasNote_equalsExpected() {
        try {
            CountryNote countryNote = new CountryNote("random string", new Country("SG"));
            CountryNoteAddCommand wrongCommand = new CountryNoteAddCommand(countryNote);
            assertNotEquals(wrongCommand, parser.parse(" c/SG nt/random string"));
            Set<Tag> tags = new HashSet<>();
            tags.add(Tag.UNTAGGED);
            countryNote.setTags(tags);
            CountryNoteAddCommand expected = wrongCommand;
            assertEquals(expected, parser.parse(" c/SG nt/random string"));
        } catch (ParseException e) {
            fail();
        }
    }

}
