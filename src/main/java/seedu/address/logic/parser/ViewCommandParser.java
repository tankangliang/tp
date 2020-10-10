package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClientViewCommand object
 */
public class ViewCommandParser implements Parser<ClientViewCommand> {
    //TODO: Test
    /**
     * Parses the given {@code String} of arguments in the context of the ClientViewCommand
     * and returns a ClientViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ClientViewCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new ClientViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientViewCommand.MESSAGE_USAGE));
        }
    }
}
