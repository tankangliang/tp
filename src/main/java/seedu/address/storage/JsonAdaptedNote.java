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
    public JsonAdaptedNote(@JsonProperty("noteContents") String contents,
            @JsonProperty("countryCode") String countryCode,
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
        this.contents = note.getNoteContents();
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
     * Returns the correct Note object being represented by this json note.
     *
     * @return The correct Note object being represented by this json note.
     */
    public Note toModelType() throws IllegalValueException {
        if (isClientNote()) {
            Set<Tag> clientNoteTags = new HashSet<>();
            for (JsonAdaptedTag tag : this.tags) {
                clientNoteTags.add(tag.toModelType());
            }
            Note clientNote = new Note(contents);
            clientNote.setTags(clientNoteTags);
            return clientNote;
        } else {
            return new CountryNote(contents, new Country(countryCode));
        }
    }

}
