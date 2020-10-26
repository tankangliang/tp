package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CountryNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

public class CountryNoteAddCommandParserTest {

    private final CountryNoteAddCommandParser parser = new CountryNoteAddCommandParser();

    @Test
    public void parse_noCountryNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" random string"));
    }

    @Test
    public void parse_withPreamable_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" random string c/SG nt/abc"));
        assertThrows(ParseException.class, () -> parser.parse(" random string c/SG nt/"));
        assertThrows(ParseException.class, () -> parser.parse(" random string c/ nt/abc"));
        assertThrows(ParseException.class, () -> parser.parse(" random string c/ nt/"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/SG nt/abc"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/SG nt/"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/ nt/abc"));
        assertThrows(ParseException.class, () -> parser.parse(" 123 c/ nt/"));
    }

    @Test
    public void parse_hasCountryNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/SG random string"));
    }

    @Test
    public void parse_invalidCountryHasNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/Z Z nt/random string"));
    }

    @Test
    public void parse_validCountryNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/MY "));
    }

    @Test
    public void parse_validCountryHasNote_equalsExpected() {
        try {
            CountryNoteAddCommand expected = new CountryNoteAddCommand(
                    new CountryNote("random string", new Country("SG")));
            assertEquals(expected, parser.parse(" c/SG nt/random string"));
        } catch (ParseException e) {
            fail();
        }
    }

}
