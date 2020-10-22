package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimezoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timezone(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTimezone = "";
        assertThrows(IllegalArgumentException.class, () -> new Timezone(invalidTimezone));
    }

    @Test
    public void isValidTimezone() {
        // null timezone
        assertThrows(NullPointerException.class, () -> Timezone.isValidTimezone(null));

        // invalid timezone
        assertFalse(Timezone.isValidTimezone("")); // empty string
        assertFalse(Timezone.isValidTimezone(" ")); // spaces only
        assertFalse(Timezone.isValidTimezone("UTC+8")); // does not start with GMT
        assertFalse(Timezone.isValidTimezone("GM+8")); // misspelled GMT
        assertFalse(Timezone.isValidTimezone("GMT+15")); // out of range (positive)
        assertFalse(Timezone.isValidTimezone("GMT-13")); // out of range (negative)
        assertFalse(Timezone.isValidTimezone("GMT+654657987654456")); // large number

        // valid timezone
        assertTrue(Timezone.isValidTimezone("GMT+8")); // valid timezone
        assertTrue(Timezone.isValidTimezone("GMT-12")); // smallest negative offset
        assertTrue(Timezone.isValidTimezone("GMT+14")); // largest positive offset

    }

    @Test
    public void getCurrHourInTimezone() {
        // use System clock to test
        // adapted from https://www.w3resource.com/java-exercises/datatypes/java-datatype-exercise-5.php
        for (int timezoneOffset = -1 * Timezone.SMALLEST_NEGATIVE_OFFSET;
            timezoneOffset <= Timezone.LARGEST_POSITIVE_OFFSET; timezoneOffset++) {
            long totalMilliseconds = System.currentTimeMillis();
            long totalSeconds = totalMilliseconds / 1000;
            long currentSecond = totalSeconds % 60;
            long totalMinutes = totalSeconds / 60;
            long currentMinute = totalMinutes % 60;
            long totalHours = totalMinutes / 60;
            long currentHour = ((totalHours + timezoneOffset) % 24);

            String value = "GMT" + (timezoneOffset >= 0 ? "+" : "") + timezoneOffset;
            assertEquals(currentHour, new Timezone(value).getCurrHourInTimezone());
        }
    }
}
