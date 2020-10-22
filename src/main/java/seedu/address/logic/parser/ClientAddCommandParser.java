package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTRACT_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ClientAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ContractExpiryDate;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Timezone;
import seedu.address.model.country.Country;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ClientAddCommand object
 */
public class ClientAddCommandParser implements Parser<ClientAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClientAddCommand
     * and returns an ClientAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClientAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_COUNTRY, PREFIX_TIMEZONE, PREFIX_CONTRACT_EXPIRY_DATE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COUNTRY,
                PREFIX_TIMEZONE, PREFIX_CONTRACT_EXPIRY_DATE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientAddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Country country = ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get());
        Timezone timezone = ParserUtil.parseTimezone(argMultimap.getValue(PREFIX_TIMEZONE).get());
        ContractExpiryDate contractExpiryDate =
                ParserUtil.parseContractExpiryDate(argMultimap.getValue(PREFIX_CONTRACT_EXPIRY_DATE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Client client = new Client(name, phone, email, address, country, timezone, contractExpiryDate, tagList);

        return new ClientAddCommand(client);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
