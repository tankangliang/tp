package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CountryNoteDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * A class that parses the user input and returns a CountryNoteDeleteCommand.
 */
public class CountryNoteDeleteCommandParser implements Parser<CountryNoteDeleteCommand> {

    @Override
    public CountryNoteDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CountryNoteDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CountryNoteDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
