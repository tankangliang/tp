package seedu.address.model.country;

import java.util.HashMap;
import java.util.Locale;

/**
 * A high-level class responsible for mapping ISO3166 2-letter country codes to countries.
 */
public class CountryManager {

    //TODO: Should include checking for 3-letter Country Code?
    private final HashMap<String, Country> countryCodeMap;

    /**
     * Initializes a CountryManager with a Map that maps ISO3166 2-letter country codes to countries.
     */
    public CountryManager() {
        countryCodeMap = initCountryCodeMap();
    }

    private static HashMap<String, Country> initCountryCodeMap() {
        HashMap<String, Country> countryCodeMap = new HashMap<>();
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            countryCodeMap.put(countryCode, new Country(countryCode));
        }
        return countryCodeMap;
    }

    /**
     * Returns the country that is identified by the given country code.
     *
     * @param countryCode The ISO3166 2-letter country code of the particular country.
     * @return The country that is identified by the given country code.
     */
    public Country getCountryFromCode(String countryCode) {
        if (countryCodeMap.containsKey(countryCode)) {
            return countryCodeMap.get(countryCode);
        } else {
            throw new NullPointerException();
        }
    }
}
