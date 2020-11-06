package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Client's timezone in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimezone(String)}
 */
public class Timezone {

    /** Timezone must start with "GMT" */
    private static final String GMT_STRING = "GMT";

    private static final String VALIDATION_REGEX = "^" + GMT_STRING + "(?<offset>[+-]\\d\\d:\\d\\d)";

    private static final Pattern TIMEZONE_FORMAT = Pattern.compile(VALIDATION_REGEX);

    public static final Set<String> VALID_TIMEZONES = new HashSet<>();

    static {
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        List<String> zoneList = new ArrayList<>(allZones);
        for (String s : zoneList) {
            ZoneOffset offset = LocalDateTime.now().atZone(ZoneId.of(s)).getOffset();
            String out = String.format("%s", offset);
            VALID_TIMEZONES.add(out);
        }
        VALID_TIMEZONES.remove("Z");
        VALID_TIMEZONES.add("+00:00");
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Timezone should be in the form \"" + GMT_STRING + "+HH:MM\" or \"" + GMT_STRING
            + "-HH:MM\" where HH is the offset in hours and MM is the offset in minutes. The full list of valid "
            + "timezones can be found at https://www.timeanddate.com/time/current-number-time-zones.html";

    public final ZoneId zoneOffsetId;

    /**
     * Constructs a {@code Timezone}.
     *
     * @param timezone A valid timezone.
     */
    public Timezone(String timezone) {
        requireNonNull(timezone);
        checkArgument(isValidTimezone(timezone), MESSAGE_CONSTRAINTS);
        assert (VALID_TIMEZONES.contains(timezone.substring(GMT_STRING.length())));

        this.zoneOffsetId = ZoneId.of(timezone);
    }

    /**
     * Returns true if a given string is a valid timezone.
     */
    public static boolean isValidTimezone(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            final Matcher matcher = TIMEZONE_FORMAT.matcher(test);
            matcher.find();
            final String offset = matcher.group("offset");
            if (VALID_TIMEZONES.contains(offset)) {
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
        ZonedDateTime date = ZonedDateTime.now(zoneOffsetId);
        return date.getHour();
    }

    /**
     * Returns the corresponding java.time.TimeZone object for this Timezone object.
     */
    public TimeZone getJavaTimeZone() {
        return TimeZone.getTimeZone(zoneOffsetId);
    }

    @Override
    public String toString() {
        String zoneOffsetIdString =  zoneOffsetId.toString();
        if (zoneOffsetIdString.equals(GMT_STRING)) {
            zoneOffsetIdString = GMT_STRING + "+00:00";
        }
        return zoneOffsetIdString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timezone // instanceof handles nulls
                && zoneOffsetId.equals(((Timezone) other).zoneOffsetId)); // state check
    }

    @Override
    public int hashCode() {
        return zoneOffsetId.hashCode();
    }

}
