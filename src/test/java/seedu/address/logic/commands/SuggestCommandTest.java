package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

public class SuggestCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuggestCommand(null));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuggestCommand(Collections.emptyList()).execute(null));
    }

    @Test
    public void execute_emptyList_success() {
        List<Client> beforeClientList = new ArrayList<>(model.getFilteredClientList());
        SuggestCommand suggestCommand = new SuggestCommand(Collections.emptyList());
        CommandResult expectedResult = new CommandResult(SuggestCommand.MESSAGE_SUGGEST_SUCCESS);
        assertEquals(suggestCommand.execute(model), expectedResult);
        List<Client> afterClientList = new ArrayList<>(model.getFilteredClientList());
        assertEquals(beforeClientList, afterClientList);
    }

    @Test
    public void execute_listPredicatesAllTrue_noChangeToClientList() {
        List<Client> beforeClientList = new ArrayList<>(model.getFilteredClientList());
        List<Predicate<Client>> predicateList = Arrays.asList(c -> true, c -> true);
        SuggestCommand suggestCommand = new SuggestCommand(predicateList);
        CommandResult expectedResult = new CommandResult(SuggestCommand.MESSAGE_SUGGEST_SUCCESS);
        assertEquals(suggestCommand.execute(model), expectedResult);
        List<Client> afterClientList = new ArrayList<>(model.getFilteredClientList());
        assertEquals(beforeClientList, afterClientList);
    }

    @Test
    public void execute_listPredicatesOneFalse_emptyClientList() {
        assertNotEquals(model.getFilteredClientList().size(), 0);
        List<Predicate<Client>> predicateList = Arrays.asList(c -> false, c -> true);
        SuggestCommand suggestCommand = new SuggestCommand(predicateList);
        CommandResult expectedResult = new CommandResult(SuggestCommand.MESSAGE_SUGGEST_SUCCESS);
        assertEquals(suggestCommand.execute(model), expectedResult);
        assertEquals(model.getFilteredClientList().size(), 0);
    }

    @Test
    public void execute_listPredicates_correctClientList() {
        assertNotEquals(model.getFilteredClientList().size(), 0);
        List<Predicate<Client>> predicateList =
                Arrays.asList(c -> c.equals(ALICE), c -> c.getCountry().equals(ALICE.getCountry()));
        SuggestCommand suggestCommand = new SuggestCommand(predicateList);
        CommandResult expectedResult = new CommandResult(SuggestCommand.MESSAGE_SUGGEST_SUCCESS);
        assertEquals(suggestCommand.execute(model), expectedResult);
        assertEquals(model.getFilteredClientList().size(), 1);
        assertEquals(model.getFilteredClientList().get(0), ALICE);
    }

    @Test
    public void equals() {
        List<Predicate<Client>> predicateList =
                Arrays.asList(c -> c.equals(ALICE), c -> c.getCountry().equals(ALICE.getCountry()));
        SuggestCommand suggestCommand = new SuggestCommand(predicateList);

        // same object -> returns true
        assertTrue(suggestCommand.equals(suggestCommand));

        // same values -> returns true
        SuggestCommand suggestCommandCopy = new SuggestCommand(predicateList);
        assertTrue(suggestCommand.equals(suggestCommandCopy));

        // different types -> returns false
        assertFalse(suggestCommand.equals(1));

        // null -> returns false
        assertFalse(suggestCommand.equals(null));

        // different predicate list -> returns false
        assertFalse(suggestCommand.equals(new SuggestCommand(Collections.emptyList())));
    }

}
