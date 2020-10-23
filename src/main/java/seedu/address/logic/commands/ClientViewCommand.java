package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.ui.WidgetViewOption;

/**
 * Views a client identified by its displayed index. Displayed on the widget view box.
 */
public class ClientViewCommand extends Command {

    /** Client command in the form client view index, where index points to the index of the client at current location
    of the rendered */
    public static final String COMMAND_WORD = "client view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_CLIENT_SUCCESS = "Viewing client: %1$s";

    private final Index targetIndex;

    public ClientViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(targetIndex.getZeroBased());
        model.setWidgetContent(clientToView);
        return new CommandResult(String.format(MESSAGE_VIEW_CLIENT_SUCCESS, clientToView.getName()),
                false, false, WidgetViewOption.CLIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientViewCommand // instanceof handles nulls
                && targetIndex.equals(((ClientViewCommand) other).targetIndex)); // state check
    }
}
