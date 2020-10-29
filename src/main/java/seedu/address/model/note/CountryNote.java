package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.country.Country;

/**
 * Representation of a country note.
 */
public class CountryNote extends Note implements Comparable<CountryNote> {

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
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CountryNote)) {
            return false;
        }

        // state check, super checks for note content and tags
        return super.equals(other) && country.equals(((CountryNote) other).country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country);
    }

    @Override
    public String toString() {
        return "[" + getCountry() + "] " + super.toString();
    }

    @Override
    public int compareTo(CountryNote countryNote) {
        return this.getCountry().getCountryCode().compareTo(countryNote.getCountry().getCountryCode());
    }

}
