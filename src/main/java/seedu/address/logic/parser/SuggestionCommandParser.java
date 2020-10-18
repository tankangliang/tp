package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUGGESTION;

import java.util.Set;

import seedu.address.logic.commands.SuggestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.SuggestionType;

/**
 * Parses input arguments and creates a new SuggestionCommand object
 */
public class SuggestionCommandParser implements Parser<SuggestionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SuggestionCommand
     * and returns a SuggestionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuggestionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUGGESTION);

        if (!argMultimap.getValue(PREFIX_SUGGESTION).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestionCommand.MESSAGE_USAGE));
        }

        Set<SuggestionType> suggestionTypeList =
                ParserUtil.parseSuggestionTypes(argMultimap.getAllValues(PREFIX_SUGGESTION));

        return new SuggestionCommand(suggestionTypeList);
    }

}
