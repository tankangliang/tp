package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.logic.commands.CountryNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.note.Note;

public class CountryNoteCommandParser implements Parser<CountryNoteCommand> {

    @Override
    public CountryNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COUNTRY, PREFIX_NOTE);

        Country country = ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get());
        Note countryNote = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());

        return new CountryNoteCommand(country, countryNote);
    }
}
