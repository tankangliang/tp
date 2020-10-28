package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.ALICE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;

class TagNoteMapTest {

    private Client client = new ClientBuilder(ALICE).build();
    private final Note taggedNote = new Note("jurong hill was a nice place");
    private final TagNoteMap tagNoteMap = new TagNoteMap();
    private final Tag testTag = new Tag("tagName");
    private final Set<Tag> tags;

    public TagNoteMapTest() {
        tags = new HashSet<>();
        tags.add(testTag);
    }

    @Test
    void initTagNoteMapFromClients_addClientWithTaggedNotes_doesNotThrowException() {
        taggedNote.setTags(tags);
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        assertDoesNotThrow(() -> tagNoteMap.initTagNoteMapFromClients(clients));
    }

    @Test
    void initTagNoteMapFromCountryNotes_validSetOfTaggedCountryNotes_doesNotThrowException() {
        Set<Note> inputSet = new HashSet<>();
        Tag tag = new Tag("tagName");
        Note countryNote1 = new Note("this country note will be tagged");
        countryNote1.setTags(tags);
        inputSet.add(countryNote1);
        assertDoesNotThrow(() -> this.tagNoteMap.initTagNoteMapFromCountryNotes(inputSet));
    }

    @Test
    void getNotesForTag_usesInitialisedMap_returnsTrue() {
        taggedNote.setTags(tags);
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        Set<Note> expectedNotes = new HashSet<>();
        expectedNotes.add(taggedNote);
        assertEquals(tagNoteMap.getNotesForTag(testTag), expectedNotes);
    }

    @Test
    void getUniqueTags_verifyWithNewTag_doesNotThrowExceptionReturnsTrue() throws ParseException {
        Set<Tag> expectedResult = new HashSet<>();
        expectedResult.add(new Tag("unprecedentedTag"));
        List<String> tagNameStrings = new ArrayList<>();
        tagNameStrings.add("unprecedentedTag");
        assertDoesNotThrow(() -> this.tagNoteMap.getUniqueTags(tagNameStrings));
        Set<Tag> actualResult = this.tagNoteMap.getUniqueTags(tagNameStrings);
        assertEquals(expectedResult, actualResult);
    }

    // also tests that when a tag doesn't have associated notes then it is removed from tagToNotesMap and uniqueTagsMap
    @Test
    void deleteNote_deleteSoleNoteWithSoleTag_clearsTagToNotesMapAndUniqueTagEntriesReturnsTrue() {
        taggedNote.setTags(tags);
        Set<Note> expectedNotesSet = new HashSet<>();
        expectedNotesSet.add(taggedNote);
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        assertTrue(tagNoteMap.getTagsForNote(taggedNote).equals(tags));
        assertTrue(tagNoteMap.getNotesForTag(testTag).equals(expectedNotesSet));
        tagNoteMap.deleteNote(taggedNote);
        assertFalse(tagNoteMap.getNotesForTag(testTag).equals(expectedNotesSet));
    }
    @Test
    void editNote_validInputs_replacesExistingNoteWithNewNote() {
        taggedNote.setTags(tags);
        Note newNote = new Note("new content");
        Set<Tag> newTagSet = new HashSet<>();
        Tag newTag = new Tag("unprecedentedTag");
        newTagSet.add(newTag);
        newNote.setTags(newTagSet);
        Set<Note> expectedNotesSet = new HashSet<>();
        expectedNotesSet.add(newNote);
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        assertTrue(tagNoteMap.getTagsForNote(taggedNote).equals(tags));
        tagNoteMap.editNote(taggedNote, newNote);
        assertFalse(tagNoteMap.getNotesForTag(testTag).equals(expectedNotesSet));
        assertTrue(tagNoteMap.getNotesForTag(newTag).equals(expectedNotesSet));
    }


    @Test
    void getTagsForNote_useNoteWithTwoTags_returnsTrue() {
        Tag tag2 = new Tag("tag2");
        tags.add(tag2);
        taggedNote.setTags(tags);
        this.client = new ClientBuilder(ALICE).build();
        this.client.addClientNote(taggedNote);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        Set<Tag> expectedTags = new HashSet<>();
        expectedTags.add(tag2);
        expectedTags.add(testTag);
        Set<Tag> actualTags = tagNoteMap.getTagsForNote(taggedNote);
        assertTrue(expectedTags.equals(actualTags));
    }

    @Test
    void equals_variousEqualityChecks_returnsTrueIfSameObjectOrState() {
        TagNoteMap emptyTagNoteMap = new TagNoteMap();
        TagNoteMap sameObject = this.tagNoteMap;
        Object randomObject = new Object();
        assertTrue(sameObject.equals(this.tagNoteMap));
        assertTrue(emptyTagNoteMap.equals(this.tagNoteMap));
        assertFalse(this.tagNoteMap.equals(randomObject));
    }
}
