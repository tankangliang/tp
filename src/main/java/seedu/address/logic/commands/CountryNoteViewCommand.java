package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.country.Country;

/**
 * Command to view all country notes of a given country.
 */
public class CountryNoteViewCommand extends Command {

    public static final String COMMAND_WORD = "country nview"; // TODO: change to country note view
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Views all country notes that are associated with the given country.\n"
        + "Parameters: "
        + PREFIX_COUNTRY + "COUNTRY_CODE "
        + "Example: " + COMMAND_WORD + " " + PREFIX_COUNTRY + "SG";
    private static final String MESSAGE_SUCCESS = "Showing country notes for %1$s";
    private final Country country;

    /**
     * Initializes a CountryNoteViewCommand.
     *
     * @param country The country which all displayed country notes belong to.
     */
    public CountryNoteViewCommand(Country country) {
        this.country = country;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredCountryNoteList(countryNote -> countryNote.getCountry().equals(country));
        return new CommandResult(String.format(MESSAGE_SUCCESS, country), false, false, false, true);
    }
}
