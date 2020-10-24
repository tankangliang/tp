package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;

import seedu.address.logic.commands.CountryNoteViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;

/**
 * Parses input arguments and creates a new CountryNoteViewCommand object
 */
public class CountryNoteViewCommandParser implements Parser<CountryNoteViewCommand> {

    @Override
    public CountryNoteViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COUNTRY);

        if (argMultimap.getValue(PREFIX_COUNTRY).isEmpty()) {
            return new CountryNoteViewCommand();
        }
        Country country = ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get());

        return new CountryNoteViewCommand(country);
    }
}
