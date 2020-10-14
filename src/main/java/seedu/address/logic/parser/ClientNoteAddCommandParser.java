package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new ClientNoteAddCommand object.
 */
public class ClientNoteAddCommandParser implements Parser<ClientNoteAddCommand> {

    /**
     * @param userInput The user input String.
     * @return A ClientNoteAddCommand corresponding to the input string.
     * @throws ParseException If user input is invalid.
     */
    @Override
    public ClientNoteAddCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, /* PREFIX_TAG,*/ PREFIX_NOTE);
        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientNoteAddCommand.MESSAGE_USAGE));
        }
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClientNoteAddCommand.MESSAGE_USAGE), pe);
        }
        Note clientNote = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ClientNoteAddCommand.MESSAGE_USAGE))));
        return new ClientNoteAddCommand(index, clientNote);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}