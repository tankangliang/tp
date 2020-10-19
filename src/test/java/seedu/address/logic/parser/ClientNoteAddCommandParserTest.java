package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

class ClientNoteAddCommandParserTest {

    private static final String PREAMBLE = "client note add";
    private static final Tag TAG = new Tag("testTag");
    private static final Tag UNTAGGED_TAG = new Tag("untagged");
    private static final String SPACE = " ";
    private static final String INDEX_STRING = "1";
    private static final String NOTE_STRING = "yay this be a note";
    private static final String NO_INDEX_NO_NOTE = "just an empty string here";
    private static final String HAS_INDEX_NO_NOTE = INDEX_STRING + " nope no note here";
    private static final String HAS_INDEX_HAS_NOTE = INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;

    @Test
    void parse_invalidFormatNoNote_throwsParseException() throws ParseException {
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_invalidFormatNoIndex_throwsParseException() throws ParseException {
        String userInput = PREAMBLE + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_validFormatUntaggedNote_successful() throws ParseException {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.addTag(UNTAGGED_TAG);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();
        assertDoesNotThrow(() -> parser.parse(userInput));
        ClientNoteAddCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    void parse_validFormatTaggedNote_doesNotThrowExceptionAndCreatesCorrectCommand() throws ParseException {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.addTag(TAG);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE + PREFIX_TAG
                + SPACE + TAG.tagName + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();
        assertDoesNotThrow(() -> parser.parse(userInput));
        ClientNoteAddCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }
    @Test
    void parse_emptyTagStringUntaggedNote_doesNotThrowExceptionAndCreatesCorrectCommand() throws ParseException {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.addTag(UNTAGGED_TAG);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE + PREFIX_TAG + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser();
        assertDoesNotThrow(() -> parser.parse(userInput));
        ClientNoteAddCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }
}
