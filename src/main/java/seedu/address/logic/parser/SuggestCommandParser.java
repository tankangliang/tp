package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUGGEST;

import java.util.Set;

import seedu.address.logic.commands.SuggestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.SuggestionType;

/**
 * Parses input arguments and creates a new SuggestCommand object.
 */
public class SuggestCommandParser implements Parser<SuggestCommand> {

    /**
     * Parses the given {@code args} in the context of the SuggestCommand and returns a SuggestCommand
     * object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SuggestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUGGEST);

        if (argMultimap.getValue(PREFIX_SUGGEST).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
        }

        Set<SuggestionType> suggestionTypeOrderedSet =
                ParserUtil.parseSuggestionTypes(argMultimap.getAllValues(PREFIX_SUGGEST));

        return new SuggestCommand(suggestionTypeOrderedSet);
    }

}
