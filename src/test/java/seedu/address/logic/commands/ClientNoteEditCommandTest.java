package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;

class ClientNoteEditCommandTest {

    private static final String NOTE_CONTENT_1 = "client note content 1";
    private static final String NOTE_CONTENT_2 = "client note content 2";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTbmManager(), new UserPrefs());
    }

    @Test
    public void execute_validClientIdxValidNoteIdx_generatesClientNoteEditCommandSuccessfully() {
        Index clientIdx = Index.fromOneBased(1);
        Index clientNoteIdx = Index.fromOneBased(1);
        Note clientNote1 = new Note(NOTE_CONTENT_1);
        Note clientNote2 = new Note(NOTE_CONTENT_2);
        Note newEditNote = new Note("dummy note to edit previous note");
        Model newModel = new ModelManager();
        Client client1 = new ClientBuilder().withName("client1").build();
        newModel.addClient(client1);
        newModel.addClientNote(client1, clientNote1);
        newModel.addClientNote(client1, clientNote2);

        Model expectedModel = new ModelManager();
        Client client1Copy = new ClientBuilder().withName("client1").build();
        expectedModel.addClient(client1Copy);
        expectedModel.addClientNote(client1Copy, newEditNote);
        expectedModel.addClientNote(client1Copy, clientNote2);

        CommandResult expectedResult = new CommandResult(ClientNoteEditCommand.MESSAGE_EDIT_CLIENT_NOTE_SUCCESS);
        ClientNoteEditCommand clientNoteEditCommand = new ClientNoteEditCommand(clientIdx, clientNoteIdx, newEditNote);
        assertCommandSuccess(clientNoteEditCommand, newModel, expectedResult, expectedModel);
    }

    // todo: add test to show untagged tag removal (in upcoming commit)
//    @Test
//    public void execute_addTagToExistingNote_defaultUntaggedTagIsRemoved() throws CommandException {
//        Index clientIdx = Index.fromOneBased(1);
//        Index clientNoteIdx = Index.fromOneBased(1);
//        Note untaggedNote = new Note(NOTE_CONTENT_1);
//
//        Tag newTag = new Tag("newTag");
//        Note taggedNote = new Note("initially will be untagged, then will have new tag");
//        Set<Tag> expectedTags = new HashSet<>();
//        expectedTags.add(newTag);
//        taggedNote.setTags(expectedTags);
//        Model newModel = new ModelManager();
//        Client client1 = new ClientBuilder().withName("client1").build();
//        newModel.addClient(client1);
//        newModel.addClientNote(client1, untaggedNote); // adds an untagged note, default tag of "untagged" will exist
//        newModel.initialiseTagNoteMap();
//
//        Model expectedModel = new ModelManager();
//        Client client1Copy = new ClientBuilder().withName("client1").build();
//        expectedModel.addClient(client1Copy);
//        expectedModel.addClientNote(client1Copy, taggedNote);
//        expectedModel.initialiseTagNoteMap();
//
//        CommandResult expectedResult = new CommandResult(ClientNoteEditCommand.MESSAGE_EDIT_CLIENT_NOTE_SUCCESS);
//        ClientNoteEditCommand clientNoteEditCommand = new ClientNoteEditCommand(clientIdx, clientNoteIdx, taggedNote);
//        assertCommandSuccess(clientNoteEditCommand, newModel, expectedResult, expectedModel);
//        clientNoteEditCommand.execute(model);
//    }


    @Test
    public void execute_validClientIdxValidNoteIdxNewTaggedNote_preservesTagHistory() {
        Tag oldTag = new Tag("historicalTag");
        Tag newTag = new Tag("newTag");
        Set<Tag> newTagSet = new HashSet<>();
        newTagSet.add(newTag);
        Set<Tag> tagHistory = new HashSet<>();
        tagHistory.add(oldTag);
        Set<Tag> expectedTags = new HashSet<>(tagHistory); // expected to preserve old tags and have new tags
        expectedTags.add(newTag);
        Note oldClientNote = new Note(NOTE_CONTENT_1);
        oldClientNote.setTags(tagHistory);
        Note clientNote2 = new Note(NOTE_CONTENT_2);
        Note newClientNote = new Note("dummy note to edit previous note");
        newClientNote.setTags(newTagSet);
        Model newModel = new ModelManager();
        Client client1 = new ClientBuilder().withName("client1").build();
        newModel.addClient(client1);
        newModel.addClientNote(client1, oldClientNote);
        newModel.addClientNote(client1, clientNote2);
        Model expectedModel = new ModelManager();
        Client client1Copy = new ClientBuilder().withName("client1").build();
        expectedModel.addClient(client1Copy);
        newClientNote.setTags(expectedTags); // containing old and new tags
        expectedModel.addClientNote(client1Copy, newClientNote);
        expectedModel.addClientNote(client1Copy, clientNote2);

        Index clientIdx = Index.fromOneBased(1);
        Index clientNoteIdx = Index.fromOneBased(1);
        CommandResult expectedResult = new CommandResult(ClientNoteEditCommand.MESSAGE_EDIT_CLIENT_NOTE_SUCCESS);
        ClientNoteEditCommand clientNoteEditCommand = new ClientNoteEditCommand(clientIdx,
                clientNoteIdx, newClientNote);
        assertCommandSuccess(clientNoteEditCommand, newModel, expectedResult, expectedModel);
    }

    @Test
    public void testEquals() {
        Note testNote = new Note("need for speed");
        ClientNoteEditCommand clientNoteEditCommand1 = new ClientNoteEditCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), testNote);
        ClientNoteEditCommand clientNoteEditCommand1Duplicate = new ClientNoteEditCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), testNote);
        ClientNoteEditCommand clientNoteEditCommandClient2 = new ClientNoteEditCommand(Index.fromOneBased(2),
                Index.fromOneBased(1), testNote);
        ClientNoteEditCommand clientNoteEditCommand1ClientNote2 = new ClientNoteEditCommand(Index.fromOneBased(1),
                Index.fromOneBased(2), testNote);
        Object randomObject = new Object();

        // random object -> returns false
        assertFalse(clientNoteEditCommand1.equals(randomObject));

        // same object -> returns true
        assertTrue(clientNoteEditCommand1.equals(clientNoteEditCommand1));

        // same values -> returns true
        assertTrue(clientNoteEditCommand1.equals(clientNoteEditCommand1Duplicate));

        // diff values (diff client) --> returns false
        assertFalse(clientNoteEditCommand1.equals(clientNoteEditCommandClient2));

        // diff values (diff client note) --> returns false
        assertFalse(clientNoteEditCommand1.equals(clientNoteEditCommand1ClientNote2));
    }

}
