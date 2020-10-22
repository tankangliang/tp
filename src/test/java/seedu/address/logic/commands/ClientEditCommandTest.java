package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientEditCommand.EditClientDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for ClientEditCommand.
 */
public class ClientEditCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        Client editedClient = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        assertThrows(NullPointerException.class, () -> new ClientEditCommand(INDEX_FIRST_CLIENT, null));
        assertThrows(NullPointerException.class, () -> new ClientEditCommand(null, descriptor));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        ClientEditCommand clientEditCommand = new ClientEditCommand(INDEX_FIRST_CLIENT, descriptor);

        String expectedMessage = String.format(ClientEditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(clientEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();

        ClientEditCommand clientEditCommand = new ClientEditCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(ClientEditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(clientEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        ClientEditCommand clientEditCommand = new ClientEditCommand(INDEX_FIRST_CLIENT, new EditClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());

        String expectedMessage = String.format(ClientEditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(clientEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        ClientEditCommand clientEditCommand = new ClientEditCommand(INDEX_FIRST_CLIENT,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(ClientEditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(clientEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        ClientEditCommand editCommand = new ClientEditCommand(INDEX_SECOND_CLIENT, descriptor);

        assertCommandFailure(editCommand, model, ClientEditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
        ClientEditCommand clientEditCommand = new ClientEditCommand(INDEX_FIRST_CLIENT,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(clientEditCommand, model, ClientEditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        ClientEditCommand editCommand = new ClientEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getClientList().size());

        ClientEditCommand clientEditCommand = new ClientEditCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(clientEditCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ClientEditCommand standardCommand = new ClientEditCommand(INDEX_FIRST_CLIENT, DESC_AMY);

        // same values -> returns true
        ClientEditCommand.EditClientDescriptor copyDescriptor = new EditClientDescriptor(DESC_AMY);
        ClientEditCommand commandWithSameValues = new ClientEditCommand(INDEX_FIRST_CLIENT, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ClientEditCommand(INDEX_SECOND_CLIENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ClientEditCommand(INDEX_FIRST_CLIENT, DESC_BOB)));
    }

}
