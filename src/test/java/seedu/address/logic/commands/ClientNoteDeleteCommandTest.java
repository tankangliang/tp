package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;
import seedu.address.testutil.ClientBuilder;

class ClientNoteDeleteCommandTest {

    private static final String NOTE_CONTENT_1 = "client note content 1";
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validCommand_doesNotThrowException() {
        Index client2Idx = Index.fromOneBased(2);
        Index clientNoteIdx = Index.fromOneBased(1);
        Note clientNote = new Note(NOTE_CONTENT_1);
        Model newModel = new ModelManager();
        Client client1 = new ClientBuilder().withName("client1").build();
        Client client2 = new ClientBuilder().withName("client2").build();
        newModel.addClient(client1);
        newModel.addClient(client2);
        newModel.addClientNote(client2, clientNote);

        Model expectedModel = new ModelManager();
        Client client1Copy = new ClientBuilder().withName("client1").build();
        Client client2Copy = new ClientBuilder().withName("client2").build();
        expectedModel.addClient(client1Copy);
        expectedModel.addClient(client2Copy);

        CommandResult expectedResult = new CommandResult(ClientNoteDeleteCommand.MESSAGE_DELETED_CLIENT_NOTE_SUCCESS);
        ClientNoteDeleteCommand clientNoteDeleteCommand = new ClientNoteDeleteCommand(client2Idx, clientNoteIdx);
        assertCommandSuccess(clientNoteDeleteCommand, newModel, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidClientIndex_throwsCommandException() {
        Index invalidClientIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        ClientNoteDeleteCommand clientNoteDeleteCommand =
                new ClientNoteDeleteCommand(invalidClientIndex, Index.fromOneBased(1));
        assertThrows(CommandException.class, () -> clientNoteDeleteCommand.execute(model));
    }

    @Test
    public void execute_validClientIndexInvalidClientNoteIndex_throwsCommandException() {
//        Client client = TypicalClients.ALICE;
        Client client = new ClientBuilder(ALICE).build();
        Note clientNote = new Note(NOTE_CONTENT_1);
        Index idx = Index.fromOneBased(1);
        model.addClientNote(client, clientNote);
        Index invalidClientNoteIndex = Index.fromOneBased(model.getFilteredClientNotesList().size() + 1);
        ClientNoteDeleteCommand clientNoteDeleteCommand =
                new ClientNoteDeleteCommand(idx, invalidClientNoteIndex);
        assertThrows(CommandException.class, () -> clientNoteDeleteCommand.execute(model));
    }

    @Test
    public void testEquals() {
        ClientNoteDeleteCommand clientNoteDeleteCommand1 =
                new ClientNoteDeleteCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        ClientNoteDeleteCommand clientNoteDeleteCommand1Duplicate =
                new ClientNoteDeleteCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        ClientNoteDeleteCommand clientNoteDeleteCommandClient2 =
                new ClientNoteDeleteCommand(Index.fromOneBased(2), Index.fromOneBased(1));
        ClientNoteDeleteCommand clientNoteDeleteCommand1ClientNote2 =
                new ClientNoteDeleteCommand(Index.fromOneBased(1), Index.fromOneBased(2));
        Object randomObject = new Object();

        // random object -> returns false
        assertFalse(clientNoteDeleteCommand1.equals(randomObject));

        // same object -> returns true
        assertTrue(clientNoteDeleteCommand1.equals(clientNoteDeleteCommand1));

        // same values -> returns true
        assertTrue(clientNoteDeleteCommand1.equals(clientNoteDeleteCommand1Duplicate));

        // diff values (diff client) --> returns false
        assertFalse(clientNoteDeleteCommand1.equals(clientNoteDeleteCommandClient2));

        // diff values (diff client note) --> returns false
        assertFalse(clientNoteDeleteCommand1.equals(clientNoteDeleteCommand1ClientNote2));
    }

}
