package seedu.address.model.country;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.model.note.CountryNote;

/**
 * A high-level class responsible for mapping ISO3166 countries to countryNotes.
 */
public class CountryNotesManager {
    private final Map<Country, Set<CountryNote>> countryToCountryNotesMap;
    private final Set<CountryNote> countryNoteSet;

    /**
     * Initializes a CountryNotesManager with a Map that maps ISO3166 2-letter country codes to countries.
     */
    public CountryNotesManager() {
        countryToCountryNotesMap = initCountryToCountryNotesMap();
        countryNoteSet = new LinkedHashSet<>();
    }

    /**
     * Initializes mapping from country to its countryNotes.
     *
     * @return The Mapping from country to its countryNotes.
     */
    private static Map<Country, Set<CountryNote>> initCountryToCountryNotesMap() {
        Map<Country, Set<CountryNote>> newCountryNotesMap = new LinkedHashMap<>();
        for (String countryCode : CountryCodeVerifier.getCountryCodes()) {
            newCountryNotesMap.put(new Country(countryCode), new LinkedHashSet<>());
        }
        return newCountryNotesMap;
    }

    /**
     * Checks if {@code countryNote} already exists.
     *
     * @param countryNote The note to be checked.
     * @return Whether {@code countryNote} already exists.
     */
    public boolean hasCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        return countryNoteSet.contains(countryNote);
    }

    /**
     * Adds the {@code countryNote}.
     *
     * @param countryNote The country note to be added.
     */
    public void addCountryNote(CountryNote countryNote) {
        if (!countryToCountryNotesMap.containsKey(countryNote.getCountry())) {
            assert false; // should always be a valid country
        }
        requireNonNull(countryNote);

        countryToCountryNotesMap.get(countryNote.getCountry()).add(countryNote);
        countryNoteSet.add(countryNote);
    }

    /**
     * Returns the set of notes associated to a particular country.
     *
     * @param country The country from which we get CountryNotes.
     */
    public Set<CountryNote> getCountryNote(Country country) {
        requireNonNull(country);
        return this.countryToCountryNotesMap.get(country);
    }

    /**
     * Returns all Country Notes.
     * Returning a list of all country notes prevents deep-copying.
     *
     * @return List of all country notes.
     */
    public List<CountryNote> getAllCountryNotesAsList() {
        return new ArrayList<>(countryNoteSet);
    }

}
