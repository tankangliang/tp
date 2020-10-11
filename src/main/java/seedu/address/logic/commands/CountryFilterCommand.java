package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientCountryMatchesInputCountryPredicate;

/**
 * Filters the client list based on their country.
 */
public class CountryFilterCommand extends Command {

    public static final String COMMAND_WORD = "country filter";

    private final ClientCountryMatchesInputCountryPredicate predicate;

    /**
     * Initializes CountryFilterCommand with a predicate of Client.
     *
     * @param predicate The Country predicate to filter clients by.
     */
    public CountryFilterCommand(ClientCountryMatchesInputCountryPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult("filtered according to country"); //TODO: add better msg
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CountryFilterCommand)) {
            return false;
        }

        // state check
        CountryFilterCommand c = (CountryFilterCommand) other;

        return predicate.equals(c.predicate);
    }
}
