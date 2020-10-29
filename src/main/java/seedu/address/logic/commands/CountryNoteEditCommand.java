package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.CountryNote;
import seedu.address.model.tag.Tag;
import seedu.address.ui.WidgetViewOption;

/**
 * A class that encapsulates the logic for editing country notes.
 */
public class CountryNoteEditCommand extends Command {

    public static final String COMMAND_WORD = "country note edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the country note at the given index in the last viewed country note list panel.\n"
            + "Parameters: INDEX"
            + PREFIX_NOTE + "NOTE_STRING"
            + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " SG "
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Edited country note at index %1$s: %2$s";
    private final Index targetIndex;
    private final CountryNote countryNote;
    private final Set<Tag> tags;

    /**
     * Initializes a CountryNoteEditCommand with the given targetIndex.
     *
     * @param targetIndex The given targetIndex.
     * @param countryNote The country note that contains the new note content.
     */
    public CountryNoteEditCommand(Index targetIndex, CountryNote countryNote) {
        requireAllNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.countryNote = countryNote;
        this.tags = new HashSet<>();
    }

    /**
     * Initializes a CountryNoteEditCommand with the given targetIndex.
     *
     * @param targetIndex The given targetIndex.
     * @param tags The new tags to add to the country note at the existing targetIndex.
     */
    public CountryNoteEditCommand(Index targetIndex, Set<Tag> tags) {
        requireAllNonNull(targetIndex, tags);
        this.targetIndex = targetIndex;
        this.countryNote = CountryNote.NULL_COUNTRY_NOTE;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CountryNote> lastShownList = model.getFilteredCountryNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COUNTRY_NOTE_DISPLAYED_INDEX);
        }

        CountryNote countryNoteToEdit = lastShownList.get(targetIndex.getZeroBased());
        CountryNote newCountryNote;
        tags.addAll(countryNoteToEdit.getTags());

        if (countryNote.equals(CountryNote.NULL_COUNTRY_NOTE)) {
            newCountryNote = new CountryNote(countryNoteToEdit);
        } else {
            newCountryNote = countryNote.setCountry(countryNoteToEdit.getCountry());
            tags.addAll(countryNote.getTags());
        }

        if (tags.size() > 1 && tags.contains(Tag.UNTAGGED)) {
            tags.remove(Tag.UNTAGGED);
        }

        newCountryNote = newCountryNote.setCountry(countryNoteToEdit.getCountry());
        newCountryNote.setTags(tags);

        model.setCountryNote(countryNoteToEdit, newCountryNote);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(), newCountryNote), false, false,
                WidgetViewOption.generateNullWidgetOption());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CountryNoteEditCommand)) {
            return false;
        }

        // state check
        CountryNoteEditCommand c = (CountryNoteEditCommand) other;

        return targetIndex.equals(c.targetIndex) && countryNote.equals(c.countryNote) && tags.equals(c.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndex, countryNote, tags);
    }
}
