package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.logic.commands.CountryNoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new CountryNoteCommand object.
 */
public class CountryNoteCommandParser implements Parser<CountryNoteAddCommand> {

    /**
     * Parses the given {@code arg} of arguments in the context of the CountryNoteAddCommand and returns a
     * CountryNoteAddCommand object for execution.
     *
     * @param args The user input string.
     * @return A CountryNoteAddCommand object which corresponds to the user input string.
     * @throws ParseException If user input string is invalid.
     */
    public CountryNoteAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COUNTRY, PREFIX_NOTE);

        if (argMultimap.getValue(PREFIX_COUNTRY).isEmpty() || argMultimap.getValue(PREFIX_NOTE).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CountryNoteAddCommand.MESSAGE_USAGE));
        }
        Country country = ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get());
        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
        CountryNote countryNote = new CountryNote(note.getNoteContents(), country);

        return new CountryNoteAddCommand(countryNote);
    }
}
