package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SuggestionTypeTest {

    @Test
    public void getSuggestionPredicate_byAvailable_suggestAvailabilityPredicate() {
        SuggestionType available = new SuggestionType(SuggestionType.BY_AVAILABLE);
        assertTrue(available.getSuggestionPredicate() instanceof SuggestAvailabilityPredicate);
    }

}
