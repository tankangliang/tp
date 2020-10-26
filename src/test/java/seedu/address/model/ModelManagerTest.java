package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.client.SuggestionType;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.model.note.TagNoteMap;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.ClientBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void deleteClient_deleteExistingClient_returnsTrue() {
        Client target = new ClientBuilder(ALICE).build();
        modelManager.addClient(target);
        assertTrue(modelManager.hasClient(ALICE));
        modelManager.deleteClient(target);
        assertFalse(modelManager.hasClient(target));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        Client client = new ClientBuilder(ALICE).build();
        assertFalse(modelManager.hasClient(client));
        modelManager.addClient(client);
        assertTrue(modelManager.hasClient(client));
    }

    @Test
    public void addAndHasCountryNote_validCountry_updatesCorrectly() {
        Country country = new Country("SG");
        CountryNote genericNote = new CountryNote("generic note", country);
        assertFalse(modelManager.hasCountryNote(genericNote));

        modelManager.addCountryNote(genericNote);
        assertTrue(modelManager.hasCountryNote(genericNote));
    }

    @Test
    public void getSortedFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getSortedFilteredClientList().remove(0));
    }

    @Test
    public void addAndHasClientNote_validSyntax_updatesCorrectly() {
        Client client = new ClientBuilder(ALICE).build();
        Note clientNote = new Note("this be a client note");
        assertFalse(modelManager.hasClientNote(client, clientNote));
        modelManager.addClientNote(client, clientNote);
        assertTrue(modelManager.hasClientNote(client, clientNote));
    }

    @Test
    public void deleteClientNote_validSyntax_deletesSuccessfully() {
        Client client = new ClientBuilder(ALICE).build();
        UserPrefs userPrefs = new UserPrefs();
        AddressBook addressBook = new AddressBookBuilder().withClient(client).build();
        modelManager = new ModelManager(addressBook, userPrefs);
        Note clientNote = new Note("this be a client note");
        modelManager.addClientNote(client, clientNote);
        assertTrue(modelManager.hasClientNote(client, clientNote));
        modelManager.initialiseTagNoteMap();
        modelManager.deleteClientNote(client, clientNote);
        assertFalse(modelManager.hasClientNote(client, clientNote));
    }

    @Test
    public void getTagNoteMap_returnUninitialisedTagNoteMap_returnsTrue() {
        TagNoteMap expected = new TagNoteMap();
        assertTrue(modelManager.getTagNoteMap().equals(expected));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different sortedList -> returns false
        modelManagerCopy = new ModelManager(addressBook, userPrefs);
        modelManagerCopy.updateSortedFilteredClientList((client1, client2) -> 1);
        assertFalse(modelManager.equals(modelManagerCopy));

        // different tagNoteMap -> returns false
        modelManagerCopy = new ModelManager(addressBook, userPrefs);
        modelManagerCopy.addClientNote(new ClientBuilder().build(), new Note("client note"));
        assertFalse(modelManager.equals(modelManagerCopy));
    }

    @Test
    public void initializeTagNoteMap_validInputs_successful() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("tagName"));
        Note taggedNote = new Note("jurong hill was a nice place");
        taggedNote.setTags(tags);
        Client aliceTagged = new ClientBuilder(ALICE).build();
        aliceTagged.addClientNote(taggedNote);
        this.modelManager.addClient(aliceTagged);
        assertDoesNotThrow(() -> this.modelManager.initialiseTagNoteMap());
    }

    @Test
    public void addCountryNote_validCountryNote_addCountryNote() {
        CountryNote countryNote = new CountryNote("some", new Country("SG"));
        assertFalse(modelManager.hasCountryNote(countryNote));
        modelManager.addCountryNote(countryNote);
        assertTrue(modelManager.hasCountryNote(countryNote));
    }

    @Test
    public void updateFilteredCountryNoteList_truePredicate_showAllCountryNotes() {
        int initialSize = modelManager.getFilteredCountryNoteList().size();
        modelManager.updateFilteredCountryNoteList(countryNote -> true);
        assertEquals(initialSize, modelManager.getFilteredCountryNoteList().size());
    }

    @Test
    public void updateFilteredCountryNoteList_falsePredicate_showNoneCountryNotes() {
        modelManager.updateFilteredCountryNoteList(countryNote -> false);
        assertEquals(0, modelManager.getFilteredCountryNoteList().size());
    }

    @Test
    public void updateFilteredCountryNoteList_countryPredicate_showRelevantCountryNotes() {
        modelManager.addCountryNote(new CountryNote("random", new Country("SG")));
        modelManager.updateFilteredCountryNoteList(countryNote -> true);
        int expect = (int) modelManager.getFilteredCountryNoteList()
                .stream()
                .filter(countryNote -> countryNote.getCountry().equals(new Country("SG")))
                .count();
        modelManager.updateFilteredCountryNoteList(countryNote -> countryNote.getCountry().equals(new Country("SG")));
        assertEquals(expect, modelManager.getFilteredCountryNoteList().size());
    }

    @Test
    public void deleteCountryNote_validCountryNote_updateCountryNoteList() {
        modelManager.addCountryNote(new CountryNote("random", new Country("SG")));
        modelManager.updateFilteredCountryNoteList(countryNote -> true);
        int initial = modelManager.getFilteredCountryNoteList().size();
        modelManager.deleteCountryNote(modelManager.getFilteredCountryNoteList().get(0));
        assertEquals(initial - 1, modelManager.getFilteredCountryNoteList().size());
    }

    @Test
    public void updateSortedFilteredClientList_zeroComparator_sameOrderOfClients() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON).build();
        ModelManager modelManagerCopy = new ModelManager(addressBook, new UserPrefs());
        modelManagerCopy.updateSortedFilteredClientList((client1, client2) -> 0);
        assertEquals(modelManagerCopy, new ModelManager(addressBook, new UserPrefs()));
    }

    @Test
    public void updateSortedFilteredClientList_contractComparator_correctOrderOfClients() {
        Client client1 = new ClientBuilder().withName("client1").withContractExpiryDate("2-3-2020").build();
        Client client2 = new ClientBuilder().withName("client2").withContractExpiryDate("1-3-2020").build();
        AddressBook addressBook = new AddressBookBuilder().withClient(client1).withClient(client2).build();
        ModelManager modelManagerCopy = new ModelManager(addressBook, new UserPrefs());
        assertEquals(modelManagerCopy.getSortedFilteredClientList().get(0), client1);
        modelManagerCopy.updateSortedFilteredClientList(
                new SuggestionType(SuggestionType.BY_CONTRACT).getSuggestionComparator());
        assertEquals(modelManagerCopy.getSortedFilteredClientList().get(0), client2);
    }

    @Test
    public void updateSortedFilteredClientList_frequencyComparator_correctOrderOfClients() {
        Client client1 = new ClientBuilder().withName("client1")
                .withLastModifiedInstant("2020-01-01T00:00:00.000000Z").build();
        Client client2 = new ClientBuilder().withName("client2")
                .withLastModifiedInstant("2020-01-02T00:00:00.000000Z").build();
        AddressBook addressBook = new AddressBookBuilder().withClient(client1).withClient(client2).build();
        ModelManager modelManagerCopy = new ModelManager(addressBook, new UserPrefs());
        assertEquals(modelManagerCopy.getSortedFilteredClientList().get(0), client1);
        modelManagerCopy.updateSortedFilteredClientList(
                new SuggestionType(SuggestionType.BY_FREQUENCY).getSuggestionComparator());
        assertEquals(modelManagerCopy.getSortedFilteredClientList().get(0), client2);
    }

    /* todo future tests:
     *  run coverage for model manager test and see what's missing:
     * 1. setClient
     * 2. widgetContent setter and gettter
     * 3. getFilteredClientNotesList
     *
     * */

}
