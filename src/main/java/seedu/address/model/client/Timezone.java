package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Client's timezone in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimezone(String)}
 */
public class Timezone {

    public static final String GMT_STRING = "GMT";
    public static final int SMALLEST_NEGATIVE_OFFSET = 12;
    public static final int LARGEST_POSITIVE_OFFSET = 14;

    public static final String MESSAGE_CONSTRAINTS =
            "Timezone should be in the form \"" + GMT_STRING + "+X\" or \"" + GMT_STRING
            + "-X\" where X is a number.\n" + "Largest offset is " + GMT_STRING
            + "+" + LARGEST_POSITIVE_OFFSET + " and smallest offset is " + GMT_STRING
            + "-" + SMALLEST_NEGATIVE_OFFSET;

    /** Timezone must start with "GMT" */
    public static final String VALIDATION_REGEX = "^" + GMT_STRING + "(?<sign>[+-])(?<number>[\\d]+)";

    private static final Pattern TIMEZONE_FORMAT = Pattern.compile(VALIDATION_REGEX);

    public final int offsetValue;

    /**
     * Constructs a {@code Timezone}.
     *
     * @param timezone A valid timezone.
     */
    public Timezone(String timezone) {
        requireNonNull(timezone);
        checkArgument(isValidTimezone(timezone), MESSAGE_CONSTRAINTS);
        String offsetString = timezone.substring(GMT_STRING.length());
        int offsetValue = Integer.parseInt(offsetString);

        assert offsetValue <= LARGEST_POSITIVE_OFFSET && offsetValue >= (-1 * SMALLEST_NEGATIVE_OFFSET);
        this.offsetValue = offsetValue;
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
            if (numberString.length() > 2) {
                return false;
            }
            final int number = Integer.parseInt(numberString);
            final int offset = sign.equals("+") ? LARGEST_POSITIVE_OFFSET : SMALLEST_NEGATIVE_OFFSET;

            if (number <= offset) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the current hour in this timezone.
     *
     * @return The current hour in this timezone.
     */
    public int getCurrHourInTimezone() {
        ZoneOffset zoneOffSet = ZoneOffset.ofHours(offsetValue);
        OffsetDateTime date = OffsetDateTime.now(zoneOffSet);
        return date.getHour();
    }

    /**
     * Returns the corresponding java.time.TimeZone object for this Timezone object.
     */
    public ZoneId getZoneId() {
        return ZoneId.of(toString());
    }

    @Override
    public String toString() {
        // Negative offset values will automatically include "-"
        String sign = offsetValue >= 0 ? "+" : "";
        return GMT_STRING + sign + offsetValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timezone // instanceof handles nulls
                && offsetValue == (((Timezone) other).offsetValue)); // state check
    }

    @Override
    public int hashCode() {
        return offsetValue;
    }

}
