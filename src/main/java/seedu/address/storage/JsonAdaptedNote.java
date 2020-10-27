package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {
    private static final String NULL_COUNTRY_CODE = "NULL_CC";
    private final String contents;
    private final String countryCode;

    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("contents") String contents,
            @JsonProperty("countryCode") String countryCode) {
        this.contents = contents;
        this.countryCode = countryCode;
    }

    /**
     * Initializes this json note with the Note that it is representing.
     *
     * @param note The note that this json note will be representing.
     */
    public JsonAdaptedNote(Note note) {
        this.contents = note.getNoteContent();
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
    public Note toModelType() {
        if (isClientNote()) {
            return new Note(contents);
        } else {
            return new CountryNote(contents, new Country(countryCode));
        }
    }

}
