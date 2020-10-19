package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.model.country.Country;
import seedu.address.model.note.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    //TODO: For storing JSON notes
    @JsonCreator
    public JsonAdaptedNote(Note note) {
    }

    //TODO: For storing JSON notes
    public boolean isClientNote() {
        return true;
    }

    //TODO: For storing JSON notes
    public Country getModelCountry() {
        return new Country("SG");
    }

    //TODO: For storing JSON notes
    public Note getModelNote() {
        return new Note("some note content");
    }

    //    /**
    //     * Converts a given {@code Tag} into this class for Jackson use.
    //     */
    //    public JsonAdaptedTag(Tag source) {
    //        tagName = source.tagName;
    //    }
    //
    //    @JsonValue
    //    public String getTagName() {
    //        return tagName;
    //    }
    //
    //    /**
    //     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
    //     *
    //     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
    //     */
    //    public Tag toModelType() throws IllegalValueException {
    //        if (!Tag.isValidTagName(tagName)) {
    //            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
    //        }
    //        return new Tag(tagName);
    //    }
}
