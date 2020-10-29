package seedu.address.storage;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {
    private static final String NULL_COUNTRY_CODE = "NULL_CC";
    private final String contents;
    private final String countryCode;
    private final Set<JsonAdaptedTag> tags = new HashSet<>();

    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("contents") String contents, @JsonProperty("countryCode") String countryCode,
            @JsonProperty("noteTags") Set<JsonAdaptedTag> tags) {
        this.contents = contents;
        this.countryCode = countryCode;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Initializes this json note with the Note that it is representing.
     *
     * @param note The note that this json note will be representing.
     */
    public JsonAdaptedNote(Note note) {
        this.contents = note.getNoteContent();
        this.tags.addAll(note.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toSet()));
        if (note.isClientNote()) {
            this.countryCode = NULL_COUNTRY_CODE;
        } else {
            this.countryCode = ((CountryNote) note).getCountry().getCountryCode();
        }
    }

    /**
     * Returns whether this json note represents a client or country note.
     *
     * @return True if this json note represents a client note.
     */
    public boolean isClientNote() {
        return countryCode.equals(NULL_COUNTRY_CODE);
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {
        Set<Tag> tags = new HashSet<>();
        for (JsonAdaptedTag tag : this.tags) {
            tags.add(tag.toModelType());
        }

        if (isClientNote()) {
            Note clientNote = new Note(contents);
            clientNote.setTags(tags);
            return clientNote;
        } else {
            CountryNote countryNote = new CountryNote(contents, new Country(countryCode));
            countryNote.setTags(tags);
            return countryNote;
        }
    }

}
