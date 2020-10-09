package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.country.Country;
import seedu.address.model.note.Note;

public class CountryNoteCommand extends Command {

    private static final String MESSAGE_DUPLICATE_COUNTRY_NOTE = "The country note already exists";
    private static final String MESSAGE_SUCCESS = "Successfully added country note";

    private final Country country;
    private final Note countryNote;

    public CountryNoteCommand(Country country, Note countryNote) {
        this.country = country;
        this.countryNote = countryNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasCountryNote(country, countryNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_COUNTRY_NOTE);
        }

        model.addCountryNote(country, countryNote);
        return new CommandResult(MESSAGE_SUCCESS); // TODO: dynamically format success message
    }
}
