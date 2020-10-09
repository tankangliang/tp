package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.CountryMatchesCodePredicate;

public class CountryFilterCommand extends Command {

    public static final String COMMAND_WORD = "country filter";

    private final CountryMatchesCodePredicate predicate;

    public CountryFilterCommand(CountryMatchesCodePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult("filtered according to country"); //TODO: add better msg
    }
}
