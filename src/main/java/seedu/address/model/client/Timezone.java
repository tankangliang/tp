package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Client's timezone in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimezone(String)}
 */
public class Timezone {

    public static final String MESSAGE_CONSTRAINTS =
            "Timezone should be in the form \"GMT+X\" or \"GMT-X\" where X is a number.\n"
            + "Largest offset is GMT+14 and smallest offset is GMT-12";

    /** Timezone must start with "GMT" */
    public static final String VALIDATION_REGEX = "^GMT(?<sign>[+-])(?<number>[\\d]+)";

    private static final Pattern TIMEZONE_FORMAT = Pattern.compile(VALIDATION_REGEX);

    private static final int SMALLEST_OFFSET = 12;
    private static final int LARGEST_OFFSET = 14;

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
        if (test.matches(VALIDATION_REGEX)) {
            final Matcher matcher = TIMEZONE_FORMAT.matcher(test);
            matcher.find();
            final String sign = matcher.group("sign");
            final String numberString = matcher.group("number");
            final int number = Integer.parseInt(numberString);
            final int offset = sign.equals("+") ? LARGEST_OFFSET : SMALLEST_OFFSET;

            if (number <= offset) {
                return true;
            }
        }
        return false;
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
