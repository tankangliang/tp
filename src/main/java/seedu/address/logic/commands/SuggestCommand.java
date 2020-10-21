package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUGGEST;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.SuggestionType;

/**
 * Suggests a list of clients based on the specified suggestion types
 */
public class SuggestCommand extends Command {

    public static final String COMMAND_WORD = "suggest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Suggests a list of all clients based on the "
        + "suggestion types specified in the arguments.\n"
        + "Parameters: " + PREFIX_SUGGEST + "SUGGESTION_TYPE\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_SUGGEST + SuggestionType.BY_FREQUENCY + "\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_SUGGEST + SuggestionType.BY_AVAILABLE + "\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_SUGGEST + SuggestionType.BY_CONTRACT;
    public static final String MESSAGE_SUGGEST_SUCCESS = "Showing clients based on suggestion criteria";

    private final Predicate<Client> combinedSuggestionPredicate;

    /**
     * Initializes SuggestCommand with a SuggestionType
     */
    public SuggestCommand(Predicate<Client> combinedSuggestionPredicate) {
        requireNonNull(combinedSuggestionPredicate);
        this.combinedSuggestionPredicate = combinedSuggestionPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(combinedSuggestionPredicate);
        return new CommandResult(MESSAGE_SUGGEST_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SuggestCommand // instanceof handles nulls
            && combinedSuggestionPredicate.equals(((SuggestCommand) other).combinedSuggestionPredicate)); // state check
    }
}
