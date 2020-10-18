package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUGGESTION;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.client.SuggestionType;

/**
 * Suggests a list of clients based on the specified suggestion types
 */
public class SuggestionCommand extends Command {

    public static final String COMMAND_WORD = "suggestion";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Suggests a list of all clients based on the "
            + "suggestion types specified in the arguments.\n"
            + "Parameters: " + PREFIX_SUGGESTION + "SUGGESTION_TYPE\n"
            + "Example: " + COMMAND_WORD + " by/contact\n"
            + "Example: " + COMMAND_WORD + " by/available\n"
            + "Example: " + COMMAND_WORD + " by/priority";
    public static final String MESSAGE_SUGGESTION_SUCCESS = "Showing clients based on suggestion criteria";

    private final Set<SuggestionType> suggestionType;

    /**
     * Initializes SuggestionCommand with a SuggestionType
     * TODO: Modify to handle multiple suggestions
     */
    public SuggestionCommand(Set<SuggestionType> suggestionType) {
        this.suggestionType = suggestionType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUGGESTION_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestionCommand // instanceof handles nulls
                && suggestionType.equals(((SuggestionCommand) other).suggestionType)); // state check
    }
}
