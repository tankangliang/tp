package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.country.Country;

/**
 * Representation of a country note.
 */
public class CountryNote extends Note {

    private final Country country;

    /**
     * Initializes a country note.
     *
     * @param content The content of the country note.
     * @param country The country that is associate with the note.
     */
    public CountryNote(String content, Country country) {
        super(content);

        requireNonNull(country);
        this.country = country;
    }

    /**
     * Gets the country that is being associated with this country note.
     *
     * @return The country that is being associated with this country note.
     */
    public Country getCountry() {
        return country;
    }

    @Override
    public boolean isClientNote() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        return country.equals(((CountryNote) obj).country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country);
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getCountry() + "]";
    }
}
