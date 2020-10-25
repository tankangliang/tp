package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.model.note.TagNoteMap;
import seedu.address.model.tag.Tag;
import seedu.address.model.widget.WidgetModel;
import seedu.address.model.widget.WidgetObject;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final SortedList<Client> sortedFilteredClients;
    private final FilteredList<CountryNote> filteredCountryNotes;
    private final WidgetModel widget;
    private final TagNoteMap tagNoteMap;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.widget = WidgetModel.initWidget();
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        sortedFilteredClients = new SortedList<>(filteredClients);
        filteredCountryNotes = new FilteredList<>(this.addressBook.getCountryNoteList());
        this.tagNoteMap = new TagNoteMap();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeClient(target);
    }

    @Override
    public void setWidgetContent(Object content) {
        widget.setContent(content);
    }

    @Override
    public WidgetObject getWidgetContent() {
        return widget.getWidgetContent();
    }


    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        Set<Note> clientNotes = client.getClientNotes();
        for (Note note : clientNotes) {
            Set<Tag> tags = note.getTags();
            updateTagNoteMapWithNote(tags, note);
        }
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);
        addressBook.setClient(target, editedClient);
    }

    @Override
    public boolean hasCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        return addressBook.hasCountryNote(countryNote);
    }

    @Override
    public boolean hasClientNote(Client target, Note clientNote) {
        requireAllNonNull(target, clientNote);
        return target.hasClientNote(clientNote);
    }

    @Override
    public void addCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        addressBook.addCountryNote(countryNote);
    }

    @Override
    public void addClientNote(Client target, Note clientNote) {
        requireAllNonNull(target, clientNote);
        target.addClientNote(clientNote);
        // update the TagNoteMap:
        Set<Tag> newTags = clientNote.getTags();
        this.tagNoteMap.addTagsForNote(newTags, clientNote);
    }

    @Override
    public void updateTagNoteMapWithNote(Set<Tag> newTags, Note note) {
        this.tagNoteMap.addTagsForNote(newTags, note);
    }

    /**
     * Initialises TagNoteMap from Clients notes and Country notes.
     */
    @Override
    public void initialiseTagNoteMap() {
        this.tagNoteMap.initTagNoteMapFromClients(this.addressBook.getClientList());
        //        this.countryManager.getAllCountryNotesAsCollectionOfSets()
        //                .forEach(this.tagNoteMap::initTagNoteMapFromCountryNotes);
        // todo: initialiseTagNoteMap probably has to be changed to use AddressBook#getNoteList()
    }

    public TagNoteMap getTagNoteMap() {
        return this.tagNoteMap;
    }

    @Override
    public void deleteClientNote(Client associatedClient, Note noteToDelete) {
        requireAllNonNull(associatedClient, noteToDelete);
        this.tagNoteMap.deleteNote(noteToDelete);
        associatedClient.deleteClientNote(noteToDelete);
    }

    //=========== Filtered Client List Accessors =============================================================

    @Override
    public ObservableList<Client> getSortedFilteredClientList() {
        return sortedFilteredClients;
    }

    @Override
    public ObservableList<Note> getSortedFilteredClientNotesList() {
        // todo: depends on UI display of client notes and their index
        List<Note> clientNotes = new ArrayList<>();
        for (Client client : getSortedFilteredClientList()) {
            clientNotes.addAll(client.getClientNotes());
        }
        return FXCollections.observableList(clientNotes);
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public void updateSortedFilteredClientList(Comparator<Client> comparator) {
        requireNonNull(comparator);
        sortedFilteredClients.setComparator(comparator);
    }

    @Override
    public ObservableList<CountryNote> getFilteredCountryNoteList() {
        return filteredCountryNotes;
    }

    @Override
    public void updateFilteredCountryNoteList(Predicate<CountryNote> predicate) {
        requireNonNull(predicate);
        filteredCountryNotes.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && sortedFilteredClients.equals(other.sortedFilteredClients)
                && tagNoteMap.equals(other.tagNoteMap);
    }

}
