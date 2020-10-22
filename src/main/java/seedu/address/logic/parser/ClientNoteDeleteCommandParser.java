package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientNoteDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClientNoteDeleteCommand object.
 */
public class ClientNoteDeleteCommandParser implements Parser<ClientNoteDeleteCommand> {

    @Override
    public ClientNoteDeleteCommand parse(String restOfCommand) throws ParseException {
        requireNonNull(restOfCommand);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(restOfCommand); // no prefix needed
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClientNoteDeleteCommand.MESSAGE_USAGE));
        }
        Index targetClientIndex;
        Index targetClientNoteIndex;
        try {
            String[] splitPreamble = argMultimap.getPreamble().split(" ");
            if (splitPreamble.length != 2) { // restOfCommand: 1 1 all space delimited ==> 2 elems only
                throw new ParseException("nope");
            }
            targetClientIndex = ParserUtil.parseIndex(splitPreamble[splitPreamble.length - 2]);
            targetClientNoteIndex = ParserUtil.parseIndex(splitPreamble[splitPreamble.length - 1]);
            return new ClientNoteDeleteCommand(targetClientIndex, targetClientNoteIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClientNoteDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
