package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.testutil.ClientBuilder;

public class TbmManagerTest {

    private final TbmManager tbmManager = new TbmManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tbmManager.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tbmManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTbmManager_replacesData() {
        TbmManager newData = getTypicalTbmManager();
        tbmManager.resetData(newData);
        assertEquals(newData, tbmManager);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        TbmManagerStub newData = new TbmManagerStub(newClients);

        assertThrows(DuplicateClientException.class, () -> tbmManager.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tbmManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInTbmManager_returnsFalse() {
        assertFalse(tbmManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInTbmManager_returnsTrue() {
        tbmManager.addClient(ALICE);
        assertTrue(tbmManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInTbmManager_returnsTrue() {
        tbmManager.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(tbmManager.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tbmManager.getClientList().remove(0));
    }

    @Test
    public void addCountryNote_updateCountryNoteList() {
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        assertFalse(tbmManager.hasCountryNote(countryNote));
        tbmManager.addCountryNote(countryNote);
        assertTrue(tbmManager.hasCountryNote(countryNote));
    }

    @Test
    public void deleteCountryNote_updateCountryNoteList() {
        CountryNote countryNote = new CountryNote("random2", new Country("SG"));
        assertFalse(tbmManager.hasCountryNote(countryNote));
        tbmManager.addCountryNote(countryNote);
        assertTrue(tbmManager.hasCountryNote(countryNote));
        tbmManager.deleteCountryNote(countryNote);
        assertFalse(tbmManager.hasCountryNote(countryNote));
    }

    /**
     * A stub ReadOnlyTbmManager whose clients list can violate interface constraints.
     */
    private static class TbmManagerStub implements ReadOnlyTbmManager {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        TbmManagerStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Note> getNoteList() {
            return null;
        }

        @Override
        public ObservableList<CountryNote> getCountryNoteList() {
            return null;
        }
    }

}
