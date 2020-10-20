package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the notes list.
     * This list will not contain any duplicate notes.
     */
    ObservableList<Note> getNoteList();
}
