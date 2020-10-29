package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.country.Country;

/**
 * Representation of a country note.
 */
public class CountryNote extends Note implements Comparable<CountryNote> {

    public static final CountryNote NULL_COUNTRY_NOTE = new CountryNote("", Country.NULL_COUNTRY);

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
     * Initializes a country note by deep copying the given country note.
     *
     * @param countryNote The country note to deep copy.
     */
    public CountryNote(CountryNote countryNote) {
        super(countryNote.getNoteContent());
        this.country = countryNote.country;
    }

    /**
     * Gets the country that is being associated with this country note.
     *
     * @return The country that is being associated with this country note.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Returns a new country note with the country set as the given country.
     *
     * @param country The given country.
     * @return A new country note with the country set as the given country.
     */
    public CountryNote setCountry(Country country) {
        requireNonNull(country);
        return new CountryNote(getNoteContent(), country);
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
        return "[" + getCountry() + "] " + super.toString();
    }

    @Override
    public int compareTo(CountryNote countryNote) {
        return this.getCountry().getCountryCode().compareTo(countryNote.getCountry().getCountryCode());
    }
}
