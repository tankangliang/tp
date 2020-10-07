package seedu.address.model.country;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import seedu.address.model.note.Note;

/**
 * A representation of a Country that can be identified by a 2-letter ISO3166 country-code or by its country
 * name. It contains a list of country notes.
 */
public class Country {

    private final String countryName;
    private final String countryCode;
    private final ArrayList<Note> countryNotes;

    /**
     * Initializes a Country by its countryCode.
     *
     * @param countryCode The ISO3166 2-letter country code of the country to be initialized.
     */
    public Country(String countryCode) {
        this.countryCode = countryCode;
        this.countryName = new Locale("", countryCode).getDisplayName();
        this.countryNotes = new ArrayList<>();
    }

    /**
     * Gets the list of country notes associated with this country.
     *
     * @return The list of country notes associated with this country.
     */
    public List<Note> getCountryNotes() {
        return Collections.unmodifiableList(this.countryNotes);
    }

    /**
     * Adds a country note for this country.
     *
     * @param countryNote The country note to be added.
     */
    public void addCountryNote(Note countryNote) {
        requireNonNull(countryNote);
        this.countryNotes.add(countryNote);
    }

    /**
     * Gets the country name of this country.
     *
     * @return The country name of this country.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Gets the country code of this country.
     *
     * @return The country code of this country.
     */
    public String getCountryCode() {
        return countryCode;
    }
}
