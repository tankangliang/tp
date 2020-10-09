package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;

import seedu.address.logic.commands.CountryFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.CountryMatchesCodePredicate;
import seedu.address.model.country.Country;
import seedu.address.model.country.CountryManager;

public class CountryFilterCommandParser implements Parser<CountryFilterCommand> {

    @Override
    public CountryFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COUNTRY);

        if (argMultimap.getValue(PREFIX_COUNTRY).isEmpty()) {
            throw new ParseException("invalid message"); // TODO: better messages
        }
        String countryCode = argMultimap.getValue(PREFIX_COUNTRY).get();
        if (!CountryManager.isValidCode(countryCode)) {
            throw new ParseException("invalid message"); // TODO: better messages
        }

        Country country = ParserUtil.parseCountry(countryCode);
        return new CountryFilterCommand(new CountryMatchesCodePredicate(country));
    }
}
