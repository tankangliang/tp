package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;


public class ClientNoteAddCommandTest {

    private static final String SPACE = " ";
    private static final String INDEX_STRING = "1";
    private static final String NOTE_STRING = "yay this be a note";
    private static final String NO_INDEX_NO_NOTE = "just an empty string here";
    private static final String HAS_INDEX_NO_NOTE = INDEX_STRING + " nope no note here";
    private static final String HAS_INDEX_HAS_NOTE = INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;
    private static final Tag UNTAGGED_TAG = new Tag("untagged");

    private final ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();


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
            Note expectedClientNote = new Note(NOTE_STRING);
            expectedClientNote.addTag(UNTAGGED_TAG);
            ClientNoteAddCommand expected = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
            ClientNoteAddCommand actual = parser.parse(HAS_INDEX_HAS_NOTE);
            assertEquals(expected, actual);
        } catch (ParseException e) {
            fail();
        }
    }
}
