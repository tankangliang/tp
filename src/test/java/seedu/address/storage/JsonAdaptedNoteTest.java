package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

public class JsonAdaptedNoteTest {

    @Test public void toModelType_validTaggedClientNote_returnsClientNote() throws IllegalValueException {
        Note taggedClientNote = new Note("some note");
        Set<Tag> associatedTags = new HashSet<>();
        associatedTags.add(new Tag("someTag"));
        taggedClientNote.setTags(associatedTags);
        JsonAdaptedNote jsonAdaptedNote = new JsonAdaptedNote(taggedClientNote);
        assertTrue(jsonAdaptedNote.toModelType().isClientNote());
    }

    @Test public void toModelType_validCountryNote_returnsCountryNote() throws IllegalValueException {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote(new CountryNote("some note", new Country("SG")));
        assertFalse(jsonAdaptedNote.toModelType().isClientNote());
    }
    /* todo: future tests:
    *       1. how to test the @JsonCreator annotated constructor? in the first place it's not used.
    * */

}
