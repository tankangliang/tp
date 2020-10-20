package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.note.TagNoteMap;
import seedu.address.model.tag.Tag;

class ClientNoteAddCommandParserTest {

    private static final String PREAMBLE = "client note add";
    private static final String SPACE = " ";
    private static final String INDEX_STRING = "1";
    private static final String NOTE_STRING = "yay this be a note";
    private static final String NO_INDEX_NO_NOTE = "just an empty string here";
    private static final String HAS_INDEX_NO_NOTE = INDEX_STRING + " nope no note here";
    private static final String HAS_INDEX_HAS_NOTE = INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;

    private final TagNoteMap tagNoteMap = new TagNoteMap();
    private final ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
    private final Tag testTag = new Tag("testTag");
    private final Set<Tag> untaggedTags;
    private final Set<Tag> tags;

    public ClientNoteAddCommandParserTest() {
        tags = new HashSet<>();
        tags.add(testTag);
        untaggedTags = new HashSet<>();
        untaggedTags.add(Tag.UNTAGGED);
    }

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
            expectedClientNote.setTags(untaggedTags);
            ClientNoteAddCommand expected = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
            ClientNoteAddCommand actual = parser.parse(HAS_INDEX_HAS_NOTE);
            assertEquals(expected, actual);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    void parse_invalidFormatNoNote_throwsParseException() {
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_invalidFormatNoIndex_throwsParseException() {
        String userInput = PREAMBLE + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_validFormatUntaggedNote_successful() throws ParseException {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(untaggedTags);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertDoesNotThrow(() -> parser.parse(userInput));
        ClientNoteAddCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    void parse_validFormatTaggedNote_doesNotThrowExceptionAndCreatesCorrectCommand() throws ParseException {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(tags);
        ClientNoteAddCommand expectedCommand = new ClientNoteAddCommand(Index.fromOneBased(1), expectedClientNote);
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE + PREFIX_TAG
                + SPACE + testTag.tagName + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertDoesNotThrow(() -> parser.parse(userInput));
        ClientNoteAddCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }
    @Test
    void parse_hasTagPrefixNoTagString_throwsParseException() {
        Note expectedClientNote = new Note(NOTE_STRING);
        expectedClientNote.setTags(untaggedTags);
        String userInput = PREAMBLE + SPACE + INDEX_STRING + SPACE + PREFIX_TAG + SPACE + PREFIX_NOTE + NOTE_STRING;
        ClientNoteAddCommandParser parser = new ClientNoteAddCommandParser(tagNoteMap);
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
