package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalClients.ALICE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

class TagNoteMapTest {

    private static final Tag TAG = new Tag("tagName");
    private static Client client = ALICE;
    private static Note taggedNote = new Note("jurong hill was a nice place");
    private TagNoteMap tagNoteMap = new TagNoteMap();

    @Test
    void initMapFromCients_addClientWithTaggedNotes_returnsTrue() {
        taggedNote.addTag(TAG);
        TagNoteMapTest.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        assertDoesNotThrow(() -> tagNoteMap.initMapFromClients(clients));
    }

    //    @Test
    //    void initMapFromCountries_addTaggedClient_addSuccessfully() {
    //        Tag tag = new Tag("tagName");
    //        Note taggedNote = new Note("this note will be tagged");
    //        taggedNote.addTag(tag);
    //        /* todo: generate country init
    //                Country
    //
    //                Client aliceTagged = ALICE;
    //                aliceTagged.addClientNote(taggedNote);
    //                List<Client> clients = new ArrayList<>();
    //                clients.add(aliceTagged);
    //                tagNoteMap.initMapFromClients(clients);
    //                Set<Note> expectedNotes = new HashSet<>();
    //                expectedNotes.add(taggedNote);
    //                assertTrue(expectedNotes.equals(tagNoteMap.getNotesForTag(tag)));
    //        */
    //    }

    @Test
    void getNotesForTag_usesInitialisedMap_returnsTrue() {
        taggedNote.addTag(TAG);
        TagNoteMapTest.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initMapFromClients(clients);
        Set<Note> expectedNotes = new HashSet<>();
        expectedNotes.add(taggedNote);
        assertEquals(tagNoteMap.getNotesForTag(TAG), expectedNotes);
    }


    @Test
    void getTagsForNote() {
    }

    @Test
    void getNotesForTag() {
    }

    @Test
    void updateTagsForNote() {
    }

    @Test
    void updateNotesForTag() {
    }
}
