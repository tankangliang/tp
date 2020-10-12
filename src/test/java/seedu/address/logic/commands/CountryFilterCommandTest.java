package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientCountryMatchesInputCountryPredicate;
import seedu.address.model.country.Country;

public class CountryFilterCommandTest {

    private static final String[] COUNTRY_CODES = Locale.getISOCountries();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validCountries_matchesExpectedFilterResult() {
        try {
            List<Client> initialClientList = new ArrayList<>();

            for (Client client : model.getFilteredClientList()) {
                initialClientList.add(client);
            }

            for (String countryCode : COUNTRY_CODES) {
                Country country = new Country(countryCode);
                ClientCountryMatchesInputCountryPredicate pred = new ClientCountryMatchesInputCountryPredicate(
                        country);
                CountryFilterCommand countryFilterCommand = new CountryFilterCommand(pred);
                countryFilterCommand.execute(model);

                List<Client> expectedFilteredClientList = initialClientList.stream().filter(pred)
                        .collect(Collectors.toList());

                int i = 0;
                for (Client client : model.getFilteredClientList()) {
                    assertTrue(client.equals(expectedFilteredClientList.get(i)));
                    i++;
                }
            }
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void equals_sameObj_returnTrue() {
        Country country = new Country("SG");
        ClientCountryMatchesInputCountryPredicate pred = new ClientCountryMatchesInputCountryPredicate(
                country);
        CountryFilterCommand countryFilterCommand = new CountryFilterCommand(pred);
        assertTrue(countryFilterCommand.equals(countryFilterCommand));
    }

    @Test
    public void equals_notCountryFilterCommand_returnFalse() {
        Country country = new Country("SG");
        ClientCountryMatchesInputCountryPredicate pred = new ClientCountryMatchesInputCountryPredicate(
                country);
        CountryFilterCommand countryFilterCommand = new CountryFilterCommand(pred);
        Object obj = new Object();
        assertFalse(countryFilterCommand.equals(obj));
    }

    @Test
    public void equals_diffPred_returnFalse() {
        Country countryFirst = new Country("SG");
        Country countrySecond = new Country("MY");
        ClientCountryMatchesInputCountryPredicate predFirst = new ClientCountryMatchesInputCountryPredicate(
                countryFirst);
        ClientCountryMatchesInputCountryPredicate predSecond = new ClientCountryMatchesInputCountryPredicate(
                countrySecond);
        CountryFilterCommand countryFilterCommandFirst = new CountryFilterCommand(predFirst);
        CountryFilterCommand countryFilterCommandSecond = new CountryFilterCommand(predSecond);
        assertFalse(countryFilterCommandFirst.equals(countryFilterCommandSecond));
        assertFalse(countryFilterCommandSecond.equals(countryFilterCommandFirst));
    }

    @Test
    public void equals_samePred_returnFalse() {
        Country countryFirst = new Country("SG");
        Country countrySecond = new Country("SG");
        ClientCountryMatchesInputCountryPredicate predFirst = new ClientCountryMatchesInputCountryPredicate(
                countryFirst);
        ClientCountryMatchesInputCountryPredicate predSecond = new ClientCountryMatchesInputCountryPredicate(
                countrySecond);
        CountryFilterCommand countryFilterCommandFirst = new CountryFilterCommand(predFirst);
        CountryFilterCommand countryFilterCommandSecond = new CountryFilterCommand(predSecond);
        assertTrue(countryFilterCommandFirst.equals(countryFilterCommandSecond));
        assertTrue(countryFilterCommandSecond.equals(countryFilterCommandFirst));
    }

}
