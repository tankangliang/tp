package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CountryNoteViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;

public class CountryNoteViewCommandParserTest {

    @Test
    public void parse_invalidCountry_throwParseException() {
        assertThrows(ParseException.class, () -> new CountryNoteViewCommandParser().parse(" c/"));
        assertThrows(ParseException.class, () -> new CountryNoteViewCommandParser().parse(" c/   "));
        assertThrows(ParseException.class, () -> new CountryNoteViewCommandParser().parse(" c/   12"));
    }

    @Test
    public void parse_noCountry_returnsExpected() {
        CountryNoteViewCommand expected = new CountryNoteViewCommand();
        try {
            CountryNoteViewCommand actual = new CountryNoteViewCommandParser().parse("");
            assertEquals(expected, actual);
            actual = new CountryNoteViewCommandParser().parse(" awfoekwof owfofew  132");
            assertEquals(expected, actual);
            actual = new CountryNoteViewCommandParser().parse("  2  3");
            assertEquals(expected, actual);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parse_validCountry_returnsExpected() {
        Country country = new Country("SG");
        CountryNoteViewCommand expected = new CountryNoteViewCommand(country);
        try {
            CountryNoteViewCommand actual = new CountryNoteViewCommandParser().parse(" c/SG");
            assertEquals(expected, actual);
            actual = new CountryNoteViewCommandParser().parse(" c/   SG");
            assertEquals(expected, actual);
        } catch (ParseException e) {
            fail();
        }
    }

}
