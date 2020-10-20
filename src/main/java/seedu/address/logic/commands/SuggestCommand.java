package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUGGEST;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.client.SuggestionType;

/**
 * Suggests a list of clients based on the specified suggestion types
 */
public class SuggestCommand extends Command {

    public static final String COMMAND_WORD = "suggest";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Suggests a list of all clients based on the "
            + "suggestion types specified in the arguments.\n"
            + "Parameters: " + PREFIX_SUGGEST + "SUGGESTION_TYPE\n"
            + "Example: " + COMMAND_WORD + " by/contact\n"
            + "Example: " + COMMAND_WORD + " by/available\n"
            + "Example: " + COMMAND_WORD + " by/priority";
    public static final String MESSAGE_SUGGEST_SUCCESS = "Showing clients based on suggestion criteria";

    private final Set<SuggestionType> suggestionTypes;

    /**
     * Initializes SuggestCommand with a SuggestionType
     * TODO: Modify to handle multiple suggestions
     */
    public SuggestCommand(Set<SuggestionType> suggestionTypes) {
        this.suggestionTypes = suggestionTypes;
    }

    @Override
    public CommandResult execute(Model model) {
        // TODO: Handle meaningful execution here
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUGGEST_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestCommand // instanceof handles nulls
                && suggestionTypes.equals(((SuggestCommand) other).suggestionTypes)); // state check
    }
}
