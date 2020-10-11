package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;
import seedu.address.testutil.TypicalClients;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

public class ClientNoteAddCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String NOTE_CONTENT_1 = "client note content 1";
    private final String NOTE_CONTENT_2 = "client note content 2";

    @Test
    public void execute_duplicateClientNote_throwsCommandException() throws CommandException {
        Client client = TypicalClients.ALICE;
        Index idx = Index.fromOneBased(1);
        Note clientNote = new Note(NOTE_CONTENT_1);
        model.addClientNote(client, clientNote);
        ClientNoteAddCommand clientNoteAddCommand = new ClientNoteAddCommand(idx, clientNote);
        assertThrows(CommandException.class, () -> clientNoteAddCommand.execute(model));
    }

    @Test
    public void execute_notDuplicateClientNote_successfullyAddsClientNote() {
        try {
            Client client = TypicalClients.ALICE;
            Index idx = Index.fromOneBased(1);
            Note clientNote1 = new Note(NOTE_CONTENT_1);
            Note clientNote2 = new Note(NOTE_CONTENT_2);
            assertFalse(model.hasClientNote(client, clientNote1));
            ClientNoteAddCommand clientNoteAddCommand1 = new ClientNoteAddCommand(idx, clientNote1);
            ClientNoteAddCommand clientNoteAddCommand2 = new ClientNoteAddCommand(idx, clientNote2);
            clientNoteAddCommand1.execute(model);
            clientNoteAddCommand2.execute(model);
            assertTrue(model.hasClientNote(client, clientNote1));
            assertTrue(model.hasClientNote(client, clientNote2));
        } catch (CommandException e) {
            fail();
        }

    }

}
