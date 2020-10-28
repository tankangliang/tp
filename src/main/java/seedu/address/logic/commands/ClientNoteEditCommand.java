package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;

/**
 * Deletes a Client-specific Note from a list of notes associated with a client.
 */
public class ClientNoteEditCommand extends Command {

    public static final String COMMAND_WORD = "client note edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a note associated to a client "
            + "by the index number used in the displayed client list "
            + "and the index number for client note in the displayed client notes list. "
            + "Parameters: CLIENT INDEX, CLIENT NOTES INDEX " + PREFIX_NOTE + "NOTE_STRING "
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NOTE + "client note newly edited content";
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

    // todo: modify this, do a statecheck b/w previous and currently edited and xfer state for unspecified one..
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownClientList = model.getSortedFilteredClientList();
        if (targetClientIndex.getZeroBased() >= lastShownClientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        List<Note> notesList = lastShownClientList.get(targetClientIndex.getZeroBased()).getClientNotesAsList();
        if (targetClientNoteIndex.getZeroBased() >= notesList.size()) {
            throw new CommandException(MESSAGE_INVALID_CLIENT_NOTE_DISPLAYED_INDEX);
        }
        Client associatedClient = lastShownClientList.get(targetClientIndex.getZeroBased());
        Note noteToEdit = associatedClient.getClientNotesAsList().get(targetClientNoteIndex.getZeroBased());
        assert associatedClient.hasClientNote(noteToEdit) : "attempting to edit client note that doesn't exist";
        // todo: Ritesh implement model's edit method
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
                && this.targetClientNoteIndex.equals(c.targetClientNoteIndex);
        // todo: question: is it better to check commmand equality using the ClientNote object or it's associated index?
    }
}
