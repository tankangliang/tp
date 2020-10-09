package seedu.address.model.client;

import java.util.function.Predicate;

import seedu.address.model.country.Country;

/**
 * Tests that a {@code Client}'s {@code Country} matches the given Country.
 */
public class CountryMatchesInputCountryPredicate implements Predicate<Client> {

    private final Country country;

    /**
     * Initializes with the country to be matched.
     *
     * @param country The country to be matched
     */
    public CountryMatchesInputCountryPredicate(Country country) {
        this.country = country;
    }

    @Override
    public boolean test(Client client) {
        return client.getCountry().equals(country);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CountryMatchesInputCountryPredicate // instanceof handles nulls
                && country.equals(((CountryMatchesInputCountryPredicate) other).country)); // state check
    }

}
