package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;

import seedu.address.logic.commands.CountryFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.CountryMatchesCodePredicate;
import seedu.address.model.country.Country;

public class CountryFilterCommandParser implements Parser<CountryFilterCommand> {

    @Override
    public CountryFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COUNTRY);

        if (argMultimap.getValue(PREFIX_COUNTRY).isEmpty()) {
            throw new ParseException("invalid message"); // TODO: better messages
        }
        Country country = ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get());

        return new CountryFilterCommand(new CountryMatchesCodePredicate(country));
    }
}
