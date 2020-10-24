package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Represents a SuggestionType in the address book.
 * Guarantees: immutable; suggestion type is valid as declared in {@link #isValidSuggestionType(String)}
 */
public class SuggestionType {

    public static final String BY_FREQUENCY = "frequency";
    public static final String BY_AVAILABLE = "available";
    public static final String BY_CONTRACT = "contract";
    public static final String MESSAGE_CONSTRAINTS = "Suggestion type can only be of the following: "
            + BY_FREQUENCY + ", " + BY_AVAILABLE + ", " + BY_CONTRACT;

    public final String suggestionString;

    /**
     * Constructs a {@code SuggestionType}.
     *
     * @param suggestionString A valid string representing one of the three suggestion types.
     */
    public SuggestionType(String suggestionString) {
        requireNonNull(suggestionString);
        checkArgument(isValidSuggestionType(suggestionString), MESSAGE_CONSTRAINTS);
        this.suggestionString = suggestionString;
    }

    /**
     * Returns this suggestion type's predicate.
     */
    public Predicate<Client> getSuggestionPredicate() {
        switch (suggestionString) {
        case BY_AVAILABLE:
            return new SuggestAvailabilityPredicate();
        case BY_CONTRACT:
            return new SuggestContractPredicate();
        case BY_FREQUENCY:
            return client -> true;
        default:
            assert false; // code execution will never reach here
            return null;
        }
    }

    /**
     * Returns this suggestion type's comparator.
     */
    public Comparator<Client> getSuggestionComparator() {
        switch (suggestionString) {
        case BY_AVAILABLE:
            return (client1, client2) -> 0;
        case BY_CONTRACT:
            return Comparator.comparing(Client::getContractExpiryDate);
        case BY_FREQUENCY:
            return Comparator.comparing(Client::getLastModifiedInstant);
        default:
            assert false; // code execution will never reach here
            return null;
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidSuggestionType(String test) {
        requireNonNull(test);
        return test.equals(BY_FREQUENCY) || test.equals(BY_AVAILABLE) || test.equals(BY_CONTRACT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestionType // instanceof handles nulls
                && suggestionString.equals(((SuggestionType) other).suggestionString)); // state check
    }

    @Override
    public int hashCode() {
        return suggestionString.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + suggestionString + ']';
    }

}
