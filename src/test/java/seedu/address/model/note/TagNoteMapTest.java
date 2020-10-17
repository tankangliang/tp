package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private Client client = ALICE;
    private final Note taggedNote = new Note("jurong hill was a nice place");
    private final TagNoteMap tagNoteMap = new TagNoteMap();

    @Test
    void initMapFromClients_addClientWithTaggedNotes_returnsTrue() {
        taggedNote.addTag(TAG);
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        assertDoesNotThrow(() -> tagNoteMap.initMapFromClients(clients));
    }

    //    @Test
    //    void initMapFromCountries_addTaggedClient_addSuccessfully() {
    //        Tag tag = new Tag("tagName");
    //        Note taggedNote = new Note("this note will be tagged");
    //        taggedNote.addTag(tag);
    //        /* todo: test  country init
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
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initMapFromClients(clients);
        Set<Note> expectedNotes = new HashSet<>();
        expectedNotes.add(taggedNote);
        assertEquals(tagNoteMap.getNotesForTag(TAG), expectedNotes);
    }

    @Test
    void getTagsForNote_useNoteWithTwoTags_returnsTrue() {
        Tag tag2 = new Tag("tag2");
        taggedNote.addTag(TAG);
        taggedNote.addTag(tag2);
        this.client = ALICE;
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initMapFromClients(clients);
        Set<Tag> expectedTags = new HashSet<>();
        expectedTags.add(tag2);
        expectedTags.add(TAG);
        Set<Tag> actualTags = tagNoteMap.getTagsForNote(taggedNote);
        assertTrue(expectedTags.equals(actualTags));
    }
}
