package seedu.address.model.country;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import seedu.address.model.note.CountryNote;

/**
 * A high-level class responsible for mapping ISO3166 countries to countryNotes.
 */
public class CountryManager {

    private static final String[] COUNTRY_CODES = Locale.getISOCountries();
    private final Map<Country, Set<CountryNote>> countryToCountryNotesMap;
    private final Set<CountryNote> countryNoteSet;

    /**
     * Initializes a CountryManager with a Map that maps ISO3166 2-letter country codes to countries.
     */
    public CountryManager() {
        countryToCountryNotesMap = initCountryToCountryNotesMap();
        countryNoteSet = new LinkedHashSet<>();
    }

    /**
     * Checks if countryCode is a valid ISO3166 code.
     *
     * @param countryCode The country code.
     * @return Whether countryCode is a valid ISO3166 code.
     */
    public static boolean isValidCountryCode(String countryCode) {
        for (int i = 0; i < COUNTRY_CODES.length; i++) {
            if (COUNTRY_CODES[i].equals(countryCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initializes mapping from country to its countryNotes.
     *
     * @return The Mapping from country to its countryNotes.
     */
    private static Map<Country, Set<CountryNote>> initCountryToCountryNotesMap() {
        Map<Country, Set<CountryNote>> newCountryNotesMap = new LinkedHashMap<>();
        for (String countryCode : COUNTRY_CODES) {
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
        return countryNoteSet.contains(countryNote);
    }

    /**
     * Adds the {@code countryNote}.
     *
     * @param countryNote The country note to be added.
     */
    public void addCountryNote(CountryNote countryNote) {
        if (!countryToCountryNotesMap.containsKey(countryNote.getCountry())) {
            return;
        }

        countryToCountryNotesMap.get(countryNote.getCountry()).add(countryNote);
        countryNoteSet.add(countryNote);
    }

    /**
     * Returns the set of notes associated to a particular country.
     *
     * @param country The country from which we get CountryNotes.
     */
    public Set<CountryNote> getCountryNote(Country country) {
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
