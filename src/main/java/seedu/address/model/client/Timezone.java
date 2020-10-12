package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's timezone in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimezone(String)}
 */
public class Timezone {

    public static final String MESSAGE_CONSTRAINTS =
            "Timezone should be in the form \"GMT+X\" or \"GMT-X\" where X is a number.";

    /*
     * Timezone must start with "GMT"
     */
    public static final String VALIDATION_REGEX = "^GMT[+-][\\d+]";

    public final String value;

    /**
     * Constructs a {@code Timezone}.
     *
     * @param timezone A valid timezone.
     */
    public Timezone(String timezone) {
        requireNonNull(timezone);
        checkArgument(isValidTimezone(timezone), MESSAGE_CONSTRAINTS);
        value = timezone;
    }

    /**
     * Returns true if a given string is a valid timezone.
     */
    public static boolean isValidTimezone(String test) {
        return test.toLowerCase().matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timezone // instanceof handles nulls
                && value.equals(((Timezone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
