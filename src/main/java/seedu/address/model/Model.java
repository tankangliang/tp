package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.country.Country;
import seedu.address.model.note.Note;
import seedu.address.model.widget.WidgetObject;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client. The client must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Sets the widget box contents to the given content.
     */
    void setWidgetContent(Object content);

    /**
     * Retrives the widget content object to be displayed in the widget.
     *
     * @return WidgetObject.
     */
    WidgetObject getWidgetContent();


    /**
     * Adds the given client.
     * <p>
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}. {@code target} must exist in the
     * address book. The client identity of {@code editedClient} must not be the same as another existing
     * client in the address book.
     */
    void setClient(Client target, Client editedClient);

    boolean hasCountryNote(Country country, Note countryNote);

    boolean hasClientNote(Client client, Note clientNote);

    void addCountryNote(Country country, Note countryNote);

    void addClientNote(Client client, Note clientNote);

    /**
     * Deletes a Client Note associated to a particular Client.
     *
     * @param associatedClient Client associated to the note to be deleted.
     * @param noteToDelete     Note to be deleted.
     */
    void deleteClientNote(Client associatedClient, Note noteToDelete);

    /**
     * Returns an unmodifiable view of the filtered client list.
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns an unmodifiable view of the filtered client notes list.
     */
    ObservableList<Note> getFilteredClientNotesList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);
}
