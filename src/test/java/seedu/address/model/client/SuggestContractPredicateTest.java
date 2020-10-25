package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class SuggestContractPredicateTest {

    private final SuggestContractPredicate predicate = new SuggestContractPredicate();

    @Test
    public void test_clientWithNullContractExpiryDate_returnsFalse() {
        Client client = new ClientBuilder().withContractExpiryDate(ContractExpiryDate.NULL_DATE).build();
        assertFalse(predicate.test(client));
    }

    @Test
    public void test_clientWithContractExpiryDate_returnsTrue() {
        Client client = new ClientBuilder().withContractExpiryDate("9-8-2029").build();
        assertTrue(predicate.test(client));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // same values -> returns true
        SuggestContractPredicate predicateCopy = new SuggestContractPredicate();
        assertTrue(predicate.equals(predicateCopy));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

        // different predicate -> returns false
        assertFalse(predicate.equals(new SuggestAvailabilityPredicate()));
    }

    @Test
    public void hashCode_test() {
        SuggestContractPredicate predicateCopy = new SuggestContractPredicate();
        // same predicate -> same hashCode
        assertEquals(predicate.hashCode(), predicateCopy.hashCode());

        // different predicate -> different hashCode
        assertNotEquals(predicate.hashCode(), new SuggestAvailabilityPredicate().hashCode());
    }

}
