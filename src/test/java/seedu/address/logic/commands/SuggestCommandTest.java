package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.ContractExpiryDate;
import seedu.address.model.client.SuggestionType;
import seedu.address.testutil.ClientBuilder;

public class SuggestCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuggestCommand(null));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuggestCommand(Collections.emptySet()).execute(null));
    }

    @Test
    public void execute_emptySet_success() {
        List<Client> beforeClientList = new ArrayList<>(model.getSortedFilteredClientList());
        SuggestCommand suggestCommand = new SuggestCommand(Collections.emptySet());
        CommandResult expectedResult = new CommandResult(SuggestCommand.MESSAGE_SUGGEST_SUCCESS);
        assertEquals(suggestCommand.execute(model), expectedResult);
        List<Client> afterClientList = new ArrayList<>(model.getSortedFilteredClientList());
        assertEquals(beforeClientList, afterClientList);
    }

    @Test
    public void execute_filterPredicates_correctClientList() {
        Model newModel = new ModelManager();
        Client client1 = new ClientBuilder().withName("client1")
                .withContractExpiryDate(ContractExpiryDate.NULL_DATE).build();
        Client client2 = new ClientBuilder().withName("client2").build();
        newModel.addClient(client1);
        newModel.addClient(client2);
        assertEquals(newModel.getSortedFilteredClientList().size(), 2);
        SuggestCommand suggestCommand = new SuggestCommand(
                Set.of(new SuggestionType(SuggestionType.BY_CONTRACT)));
        CommandResult expectedResult = new CommandResult(SuggestCommand.MESSAGE_SUGGEST_SUCCESS);
        assertEquals(suggestCommand.execute(newModel), expectedResult);
        assertEquals(newModel.getSortedFilteredClientList().size(), 1);
        assertEquals(newModel.getSortedFilteredClientList().get(0), client2);
    }

    @Test
    public void equals() {
        Set<SuggestionType> suggestionTypeSet = Set.of(new SuggestionType(SuggestionType.BY_FREQUENCY),
                new SuggestionType(SuggestionType.BY_CONTRACT), new SuggestionType(SuggestionType.BY_AVAILABLE));
        SuggestCommand suggestCommand = new SuggestCommand(suggestionTypeSet);

        // same object -> returns true
        assertTrue(suggestCommand.equals(suggestCommand));

        // same values -> returns true
        SuggestCommand suggestCommandCopy = new SuggestCommand(suggestionTypeSet);
        assertTrue(suggestCommand.equals(suggestCommandCopy));

        // different types -> returns false
        assertFalse(suggestCommand.equals(1));

        // null -> returns false
        assertFalse(suggestCommand.equals(null));

        // different predicate list -> returns false
        assertFalse(suggestCommand.equals(new SuggestCommand(Collections.emptySet())));
    }

}
