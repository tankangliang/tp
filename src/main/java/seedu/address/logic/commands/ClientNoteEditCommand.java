package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Edits a Client-specific Note from a list of notes associated with a client while preserving tagging history.
 */
public class ClientNoteEditCommand extends Command {

    public static final String COMMAND_WORD = "client note edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a note associated to a client "
            + "by the index number used in the displayed client list"
            + "and the index number for client note in the displayed client notes list.\n"
            + "Parameters: CLIENT_INDEX CLIENT_NOTE_INDEX " + PREFIX_NOTE + "NOTE_STRING [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 1 " + PREFIX_NOTE + "client note newly edited content";
    public static final String MESSAGE_EDIT_CLIENT_NOTE_SUCCESS = "Successfully edited client note";
    private static final String MESSAGE_INVALID_CLIENT_NOTE_DISPLAYED_INDEX = "The client note index provided is "
            + "invalid";
    private final Index targetClientIndex;
    private final Index targetClientNoteIndex;
    private final Note newNote;

    /**
     * Initializes a ClientNoteEditCommand.
     *  @param targetClientIndex The index of the client whom the clientNote is associated to.
     * @param targetClientNoteIndex  The index of the clientNote to be edited.
     * @param newNote The newly edited note.
     */
    public ClientNoteEditCommand(Index targetClientIndex, Index targetClientNoteIndex, Note newNote) {
        requireAllNonNull(targetClientIndex, targetClientNoteIndex);
        this.targetClientIndex = targetClientIndex;
        this.targetClientNoteIndex = targetClientNoteIndex;
        this.newNote = newNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownClientList = model.getSortedFilteredClientList();
        if (targetClientIndex.getZeroBased() >= lastShownClientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        List<Note> notesList = lastShownClientList.get(targetClientIndex.getZeroBased())
                .getClientNotesAsUnmodifiableList();
        if (targetClientNoteIndex.getZeroBased() >= notesList.size()) {
            throw new CommandException(MESSAGE_INVALID_CLIENT_NOTE_DISPLAYED_INDEX);
        }
        Client associatedClient = lastShownClientList.get(targetClientIndex.getZeroBased());
        Note noteToEdit = associatedClient.getClientNotesAsUnmodifiableList().get(targetClientNoteIndex.getZeroBased());
        assert associatedClient.hasClientNote(noteToEdit) : "attempting to edit client note that doesn't exist";
        Set<Tag> accumulatedTags = new HashSet<>();
        accumulatedTags.addAll(noteToEdit.getTags()); // these are the previous tags, because we want to retain history
        accumulatedTags.addAll(newNote.getTags());
        // because parser used tagNoteMap#getUniqueTags, it is okay for there to be duplicates in previous tags and
        // new Note's tags. Overwriting will keep one of the two duplicates, and they are the same object reference.
        if (accumulatedTags.size() > 1) {
            accumulatedTags.remove(Tag.UNTAGGED);
        }
        newNote.setTags(accumulatedTags);
        model.editClientNote(associatedClient, noteToEdit, newNote);
        return new CommandResult(MESSAGE_EDIT_CLIENT_NOTE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientNoteEditCommand)) {
            return false;
        }

        // state check
        ClientNoteEditCommand c = (ClientNoteEditCommand) other;

        return this.targetClientIndex.equals(c.targetClientIndex)
                && this.targetClientNoteIndex.equals(c.targetClientNoteIndex)
                && this.newNote.equals(c.newNote);
    }
}
