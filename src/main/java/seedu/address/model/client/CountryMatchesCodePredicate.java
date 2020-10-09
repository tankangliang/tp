package seedu.address.model.client;

import java.util.function.Predicate;

import seedu.address.model.country.Country;

/**
 * Tests that a {@code Client}'s {@code Country} matches the given Country.
 */
public class CountryMatchesCodePredicate implements Predicate<Client> {

    private final Country country;

    /**
     * Initializes with the country to be matched.
     *
     * @param country The country to be matched
     */
    public CountryMatchesCodePredicate(Country country) {
        this.country = country;
    }

    @Override
    public boolean test(Client client) {
        return client.getCountry().equals(country);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CountryMatchesCodePredicate // instanceof handles nulls
                && country.equals(((CountryMatchesCodePredicate) other).country)); // state check
    }

}
