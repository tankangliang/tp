package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a SuggestionType in the address book.
 * Guarantees: immutable; suggestion type is valid as declared in {@link #isValidSuggestionType(String)}
 */
public class SuggestionType {

    public static final String MESSAGE_CONSTRAINTS = "Suggestion type can only be of the following "
        + "\"contact\", \"available\", \"priority\"";

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
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidSuggestionType(String test) {
        return test.equals("contact") || test.equals("available") || test.equals("priority");
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
