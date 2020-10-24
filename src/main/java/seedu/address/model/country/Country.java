package seedu.address.model.country;

import java.util.Locale;

/**
 * A representation of a Country that can be identified by a 2-letter ISO3166 country-code or by its country
 * name. It contains a list of country notes.
 */
public class Country {

    private static final String NONE_COUNTRY_CODE = "";
    private static final String NONE_COUNTRY_NAME = "";

    private final String countryName;
    private final String countryCode;

    /**
     * Initializes a Country by its countryCode.
     *
     * @param countryCode The ISO3166 2-letter country code of the country to be initialized.
     */
    public Country(String countryCode) {
        assert CountryCodeVerifier.isValidCountryCode(countryCode);

        this.countryCode = countryCode;
        this.countryName = new Locale("", countryCode).getDisplayName();
    }

    private Country() {
        this.countryCode = NONE_COUNTRY_CODE;
        this.countryName = NONE_COUNTRY_NAME;
    }

    public static Country getNullCountry() {
        return new Country();
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

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Country)) {
            return false;
        }

        // state check
        Country c = (Country) other;

        return countryCode.equals(c.countryCode);
    }

    @Override
    public int hashCode() {
        return countryCode.hashCode();
    }

    @Override
    public String toString() {
        return countryName + " (" + countryCode + ")";
    }

}
