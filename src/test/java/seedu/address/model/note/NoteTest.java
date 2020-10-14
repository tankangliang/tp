package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

class NoteTest {
    private static final String NOTE_CONTENT_1 = "note content 1";
    private static final String NOTE_CONTENT_2 = "note content 2";
    private static final String DUPLICATE_NOTE_CONTENT_1 = "note content 1";
    private static final Tag TAG_1 = new Tag("tag1");
    private static final Tag TAG_2 = new Tag("tag2");
    private static final Note NOTE_1 = new Note(NOTE_CONTENT_1);

    @Test
    public void equals_sameNoteContents_returnTrue() {
        Note note1 = new Note(NOTE_CONTENT_1);
        Note note1Duplicate = new Note(DUPLICATE_NOTE_CONTENT_1);
        assertEquals(note1, note1Duplicate);
    }

    @Test
    public void equals_differentNoteContents_returnFalse() {
        Note note1 = new Note(NOTE_CONTENT_1);
        Note note2 = new Note(NOTE_CONTENT_2);
        assertNotEquals(note1, note2);
    }

    @Test
    public void equals_sameNoteContentSameTags_returnTrue() {
        Note note1 = NOTE_1;
        Note note1Duplicate = new Note(NOTE_CONTENT_1);
        note1.addTag(TAG_1);
        note1.addTag(TAG_2);
        note1Duplicate.addTag(TAG_2);
        note1Duplicate.addTag(TAG_1);
        assertEquals(note1, note1Duplicate);
    }

    @Test
    public void equals_sameNoteContentDifferentTags_returnFalse() {
        Note note1 = NOTE_1;
        Note note1Duplicate = new Note(NOTE_CONTENT_1);
        note1.addTag(TAG_1);
        note1.addTag(TAG_2);
        note1Duplicate.addTag(TAG_2);
        note1Duplicate.addTag(new Tag("rogueTag"));
        assertNotEquals(note1, note1Duplicate);
    }
}
