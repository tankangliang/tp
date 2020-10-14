package seedu.address.model.country;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import seedu.address.model.note.Note;

/**
 * A high-level class responsible for mapping ISO3166 2-letter country codes to countries.
 */
public class CountryManager {

    //TODO: Should include checking for 3-letter Country Code?
    private static final String[] COUNTRY_CODES = Locale.getISOCountries();
    private final Map<Country, Set<Note>> countryNotesMap;

    /**
     * Initializes a CountryManager with a Map that maps ISO3166 2-letter country codes to countries.
     */
    public CountryManager() {
        countryNotesMap = initCountryNotesMap();
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
    private static Map<Country, Set<Note>> initCountryNotesMap() {
        Map<Country, Set<Note>> newCountryNotesMap = new LinkedHashMap<>();
        for (String countryCode : COUNTRY_CODES) {
            newCountryNotesMap.put(new Country(countryCode), new LinkedHashSet<>());
        }
        return newCountryNotesMap;
    }

    /**
     * Checks if {@code country} contains {@code countryNote}.
     *
     * @param country     The country to be checked.
     * @param countryNote The note to be checked.
     * @return Whether {@code country} contains {@code countryNote}.
     */
    public boolean hasCountryNote(Country country, Note countryNote) {
        if (!countryNotesMap.containsKey(country)) {
            return false;
        }

        return countryNotesMap.get(country).contains(countryNote);
    }

    /**
     * Adds the {@code countryNote} to the {@code country}.
     *
     * @param country     The country where the countryNote will be added.
     * @param countryNote The country note to be added.
     */
    public void addCountryNote(Country country, Note countryNote) {
        if (!countryNotesMap.containsKey(country)) {
            return;
        }

        countryNotesMap.get(country).add(countryNote);
    }

    /**
     * Returns the set of notes associated to a particular country.
     *
     * @param country The country from which we get CountryNotes.
     */
    public Set<Note> getCountryNote(Country country) {
        return this.countryNotesMap.get(country);
    }

    /**
     * Returns all Country Notes currently in the map.
     *
     * @return Set of all country notes in the map.
     */
    public Collection<Set<Note>> getAllCountryNotes() {
        return this.countryNotesMap.values();
    }

}
