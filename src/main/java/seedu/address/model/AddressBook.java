package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.country.CountryNotesManager;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagSet;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueClientList clients;
    private final UniqueTagSet tags;
    private final CountryNotesManager countryNotesManager;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
        tags = new UniqueTagSet();
        countryNotesManager = new CountryNotesManager();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Clients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients} and the contents of the tag set with
     * the union over all client tags, then update client tag sets with unique tags.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
        Set<Tag> allClientTags = new HashSet<>();
        for (Client client : clients) {
            allClientTags.addAll(client.getTags());
        }
        this.tags.setTags(allClientTags);
        for (Client client : clients) {
            replaceClientTagSet(client);
        }
    }

    /**
     * Replaces all notes in addressbook with the given list of notes.
     *
     * @param notes The given list of notes.
     */
    public void setNotes(List<Note> notes) {
        for (Note note: notes) {
            if (note.isClientNote()) {
                // handle client notes
            } else {
                countryNotesManager.addCountryNote((CountryNote) note);
            }
        }
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setClients(newData.getClientList());
        setNotes(newData.getNoteList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Replaces the client tag set with a set of matching unique tag objects.
     */
    private void replaceClientTagSet(Client client) {
        Set<Tag> clientLocalTags = client.getTags();
        tags.addAll(clientLocalTags);
        client.replaceTags(tags.getTags(clientLocalTags));
    }

    /**
     * Replaces client tag set and adds the client to the address book.
     * The client must not already exist in the address book.
     */
    public void addClient(Client client) {
        replaceClientTagSet(client);
        clients.add(client);
    }

    /**
     * Replaces {@code editedClient} tag set and then replaces the given client {@code target} in the list
     * with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);
        replaceClientTagSet(editedClient);
        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    /**
     * Checks whether the given country has the given countryNote.
     *
     * @param countryNote The given countryNote
     * @return True if the given country has the given countryNote.
     */
    public boolean hasCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        return countryNotesManager.hasCountryNote(countryNote);
    }

    /**
     * Adds the given countryNote to the given country.
     *
     * @param countryNote The given countryNote
     */
    public void addCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        countryNotesManager.addCountryNote(countryNote);
    }

    /**
     * Deletes the given country note.
     *
     * @param countryNoteToDelete The country note to delete.
     */
    public void deleteCountryNote(CountryNote countryNoteToDelete) {
        requireNonNull(countryNoteToDelete);
        countryNotesManager.deleteCountryNote(countryNoteToDelete);
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    //TODO: add client notes also. NOTE: THIS ONLY RETURNS COUNTRY NOTES FOR NOW.
    @Override
    public ObservableList<Note> getNoteList() {
        return FXCollections.observableArrayList(countryNotesManager.asUnmodifiableObservableList());
    }

    /**
     * Gets the list of country notes in TBM as an unmodifiable observable list.
     *
     * @return The unmodifiable observable list of country notes in TBM.
     */
    public ObservableList<CountryNote> getCountryNoteList() {
        return countryNotesManager.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
