package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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
 * Represents the in-memory model of the TbmManager data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TbmManager tbmManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final SortedList<Client> sortedFilteredClients;
    private final FilteredList<CountryNote> filteredCountryNotes;
    private final WidgetModel widget;
    private final TagNoteMap tagNoteMap;

    /**
     * Initializes a ModelManager with the given tbmManager and userPrefs.
     */
    public ModelManager(ReadOnlyTbmManager tbmManager, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tbmManager, userPrefs);

        logger.fine("Initializing with TBM Manager: " + tbmManager + " and user prefs " + userPrefs);

        this.tbmManager = new TbmManager(tbmManager);
        this.userPrefs = new UserPrefs(userPrefs);
        this.widget = WidgetModel.initWidget();
        filteredClients = new FilteredList<>(this.tbmManager.getClientList());
        sortedFilteredClients = new SortedList<>(filteredClients);
        filteredCountryNotes = new FilteredList<>(this.tbmManager.getCountryNoteList());
        this.tagNoteMap = new TagNoteMap();
    }

    public ModelManager() {
        this(new TbmManager(), new UserPrefs());
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
    public Path getTbmManagerFilePath() {
        return userPrefs.getTbmManagerFilePath();
    }

    @Override
    public void setTbmManagerFilePath(Path tbmManagerFilePath) {
        requireNonNull(tbmManagerFilePath);
        userPrefs.setTbmManagerFilePath(tbmManagerFilePath);
    }

    //=========== TbmManager ================================================================================

    @Override
    public ReadOnlyTbmManager getTbmManager() {
        return tbmManager;
    }

    @Override
    public void setTbmManager(ReadOnlyTbmManager tbmManager) {
        this.tbmManager.resetData(tbmManager);
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return tbmManager.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        tbmManager.removeClient(target);
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
        tbmManager.addClient(client);
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
        tbmManager.setClient(target, editedClient);
    }

    @Override
    public boolean hasCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        return tbmManager.hasCountryNote(countryNote);
    }

    @Override
    public boolean hasClientNote(Client target, Note clientNote) {
        requireAllNonNull(target, clientNote);
        assert tbmManager.hasClient(target) : "tbmManager not synced with modelManager; missing client";
        return target.hasClientNote(clientNote);
    }

    @Override
    public void addCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        tbmManager.addCountryNote(countryNote);
    }

    @Override
    public void deleteCountryNote(CountryNote countryNoteToDelete) {
        requireNonNull(countryNoteToDelete);
        tbmManager.deleteCountryNote(countryNoteToDelete);
    }

    @Override
    public void addClientNote(Client target, Note clientNote) {
        requireAllNonNull(target, clientNote);
        target.addClientNote(clientNote);
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
        this.tagNoteMap.initTagNoteMapFromClients(this.tbmManager.getClientList());
        // todo: initialiseTagNoteMap probably has to be changed to use TbmManager#getNoteList()
        this.tagNoteMap.initTagNoteMapFromCountryNotes(new HashSet<>(this.tbmManager.getCountryNoteList()));
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

    @Override
    public void editClientNote(Client associatedClient, Note noteToEdit, Note newNote) {
        requireAllNonNull(associatedClient, noteToEdit);
        // todo: Ritesh add tagnote map's edit and client's edit methods
        this.tagNoteMap.editNote(noteToEdit, newNote);
        associatedClient.editClientNote(noteToEdit, newNote);


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
        return tbmManager.equals(other.tbmManager)
                && userPrefs.equals(other.userPrefs)
                && sortedFilteredClients.equals(other.sortedFilteredClients)
                && tagNoteMap.equals(other.tagNoteMap);
    }

}
