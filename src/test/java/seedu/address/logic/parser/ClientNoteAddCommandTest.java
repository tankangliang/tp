package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.logic.commands.CountryNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.country.NoteStub;
import seedu.address.model.note.Note;

import static org.junit.jupiter.api.Assertions.*;

public class ClientNoteAddCommandTest {


    private final ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();
    private final String INDEX_STRING = "1";
    private final String NOTE_STRING = "yay this be a note";
    private final String NO_INDEX_NO_NOTE = "just an empty string here";
    private final String HAS_INDEX_NO_NOTE = INDEX_STRING + " nope no note here";
    private final String HAS_INDEX_HAS_NOTE = INDEX_STRING + " nt/" + NOTE_STRING;

    @Test
    public void parse_noIndexNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(NO_INDEX_NO_NOTE));
    }

    @Test
    public void parse_hasIndexNoNote_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(HAS_INDEX_NO_NOTE));
    }


    @Test
    public void parse_hasIndexHasNote_equalsExpected() {
        try {
            ClientNoteAddCommand expected = new ClientNoteAddCommand(Index.fromOneBased(1),
                    new Note(NOTE_STRING));
            ClientNoteAddCommand actual = parser.parse(HAS_INDEX_HAS_NOTE);
            System.out.println("hell");
            assertEquals(expected, actual);
        } catch (ParseException e) {
            fail();
        }
    }
}
