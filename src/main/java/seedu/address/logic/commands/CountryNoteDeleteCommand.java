package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.CountryNote;
import seedu.address.ui.WidgetViewOption;

/**
 * A class that encapsulates the logic for deleting country notes.
 */
public class CountryNoteDeleteCommand extends Command {

    public static final String COMMAND_WORD = "country note delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the country note that are associated with the last viewed country at the given index.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted country note at index %1$s: %2$s";
    private final Index targetIndex;

    /**
     * Initializes a CountryNoteDeleteCommand with the given targetIndex.
     *
     * @param targetIndex The given targetIndex.
     */
    public CountryNoteDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CountryNote> lastShownList = model.getFilteredCountryNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COUNTRY_NOTE_DISPLAYED_INDEX);
        }

        CountryNote countryNoteToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCountryNote(countryNoteToDelete);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(), countryNoteToDelete), false, false,
                WidgetViewOption.generateNullWidgetOption());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CountryNoteDeleteCommand)) {
            return false;
        }

        // state check
        CountryNoteDeleteCommand c = (CountryNoteDeleteCommand) other;

        return targetIndex.equals(c.targetIndex);
    }
}
