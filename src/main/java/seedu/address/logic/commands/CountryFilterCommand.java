package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.CountryMatchesInputCountryPredicate;

/**
 * Filters the client list based on their country.
 */
public class CountryFilterCommand extends Command {

    public static final String COMMAND_WORD = "country filter";

    private final CountryMatchesInputCountryPredicate predicate;

    /**
     * Initializes CountryFilterCommand with a predicate of Client.
     *
     * @param predicate The Country predicate to filter clients by.
     */
    public CountryFilterCommand(CountryMatchesInputCountryPredicate predicate) {
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
    public boolean equals(Object obj) {
        if (!(obj instanceof CountryFilterCommand)) {
            return false;
        }
        return predicate.equals(((CountryFilterCommand) obj).predicate);
    }
}
