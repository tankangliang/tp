package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CountryNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.country.NoteStub;

public class CountryNoteCommandParserTest {
    //TODO: add more fine-grained tests and abstract out string literals
    private CountryNoteCommandParser parser = new CountryNoteCommandParser();

    @Test
    public void parse_noCountrynoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" random string"));
    }

    @Test
    public void parse_hasCountrynoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/SG random string"));
    }

    @Test
    public void parse_invalidCountryhasNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" c/ZZ nt/random string"));
    }

    @Test
    public void parse_validCountryhasNote_equalsExpected() {
        try {
            CountryNoteCommand expected = new CountryNoteCommand(new Country("SG"), new NoteStub("random string"));
            assertEquals(expected, parser.parse(" c/SG nt/random string"));
        } catch (ParseException e) {
            fail();
        }
    }

}
