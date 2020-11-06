package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.ZoneId;
import java.util.TimeZone;

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
        assertFalse(Timezone.isValidTimezone("UTC+00:00")); // does not start with GMT
        assertFalse(Timezone.isValidTimezone("GM+00:00")); // misspelled GMT
        assertFalse(Timezone.isValidTimezone("GMT+15:00")); // out of range (positive)
        assertFalse(Timezone.isValidTimezone("GMT-13:00")); // out of range (negative)
        assertFalse(Timezone.isValidTimezone("GMT+13:30")); // unrecognised timezone
        assertFalse(Timezone.isValidTimezone("GMT+654657987654456")); // large number

        // all valid timezones
        assertTrue(Timezone.isValidTimezone("GMT+14:00"));
        assertTrue(Timezone.isValidTimezone("GMT+13:45"));
        assertTrue(Timezone.isValidTimezone("GMT+13:00"));
        assertTrue(Timezone.isValidTimezone("GMT+12:00"));
        assertTrue(Timezone.isValidTimezone("GMT+11:00"));
        assertTrue(Timezone.isValidTimezone("GMT+10:30"));
        assertTrue(Timezone.isValidTimezone("GMT+10:00"));
        assertTrue(Timezone.isValidTimezone("GMT+09:30"));
        assertTrue(Timezone.isValidTimezone("GMT+09:00"));
        assertTrue(Timezone.isValidTimezone("GMT+08:45"));
        assertTrue(Timezone.isValidTimezone("GMT+08:00"));
        assertTrue(Timezone.isValidTimezone("GMT+07:00"));
        assertTrue(Timezone.isValidTimezone("GMT+06:30"));
        assertTrue(Timezone.isValidTimezone("GMT+06:00"));
        assertTrue(Timezone.isValidTimezone("GMT+05:45"));
        assertTrue(Timezone.isValidTimezone("GMT+05:30"));
        assertTrue(Timezone.isValidTimezone("GMT+05:00"));
        assertTrue(Timezone.isValidTimezone("GMT+04:30"));
        assertTrue(Timezone.isValidTimezone("GMT+04:00"));
        assertTrue(Timezone.isValidTimezone("GMT+03:30"));
        assertTrue(Timezone.isValidTimezone("GMT+03:00"));
        assertTrue(Timezone.isValidTimezone("GMT+02:00"));
        assertTrue(Timezone.isValidTimezone("GMT+01:00"));
        assertTrue(Timezone.isValidTimezone("GMT+00:00"));
        assertTrue(Timezone.isValidTimezone("GMT-01:00"));
        assertTrue(Timezone.isValidTimezone("GMT-02:00"));
        assertTrue(Timezone.isValidTimezone("GMT-03:00"));
        assertTrue(Timezone.isValidTimezone("GMT-03:30"));
        assertTrue(Timezone.isValidTimezone("GMT-04:00"));
        assertTrue(Timezone.isValidTimezone("GMT-05:00"));
        assertTrue(Timezone.isValidTimezone("GMT-06:00"));
        assertTrue(Timezone.isValidTimezone("GMT-07:00"));
        assertTrue(Timezone.isValidTimezone("GMT-08:00"));
        assertTrue(Timezone.isValidTimezone("GMT-09:00"));
        assertTrue(Timezone.isValidTimezone("GMT-09:30"));
        assertTrue(Timezone.isValidTimezone("GMT-10:00"));
        assertTrue(Timezone.isValidTimezone("GMT-11:00"));
        assertTrue(Timezone.isValidTimezone("GMT-12:00"));

    }

    @Test
    public void validTimezones_displayedCorrectly() {
        assertEquals("GMT+14:00", new Timezone("GMT+14:00").toString());
        assertEquals("GMT-12:00", new Timezone("GMT-12:00").toString());
        assertEquals("GMT+13:45", new Timezone("GMT+13:45").toString());
        assertEquals("GMT-09:30", new Timezone("GMT-09:30").toString());
        assertEquals("GMT+00:00", new Timezone("GMT+00:00").toString());

    }

    @Test
    public void getCurrHourInTimezone() {
        // use System clock to test
        // adapted from https://www.w3resource.com/java-exercises/datatypes/java-datatype-exercise-5.php
        Timezone.VALID_TIMEZONES.forEach(timezoneOffsetString -> {
            // String is in format "+HH:MM"
            String sign = timezoneOffsetString.substring(0,1);
            String hoursOffsetString = timezoneOffsetString.substring(1,3);
            String minutesOffsetString = timezoneOffsetString.substring(4);

            int hoursOffset = Integer.parseInt(sign + hoursOffsetString);
            int minutesOffset = Integer.parseInt(sign + minutesOffsetString);

            long totalMilliseconds = System.currentTimeMillis();
            long totalSeconds = totalMilliseconds / 1000;
            long totalMinutes = totalSeconds / 60;
            long totalHours = (totalMinutes + minutesOffset) / 60;
            long currentHour = ((totalHours + hoursOffset) % 24);

            String value = "GMT" + timezoneOffsetString;
            assertEquals(currentHour, new Timezone(value).getCurrHourInTimezone());
        });

    }

    @Test
    public void getTimeZone() {
        assertEquals(TimeZone.getTimeZone(ZoneId.of("GMT+14:00")), new Timezone("GMT+14:00").getJavaTimeZone());
        assertEquals(TimeZone.getTimeZone(ZoneId.of("GMT-12:00")), new Timezone("GMT-12:00").getJavaTimeZone());
        assertEquals(TimeZone.getTimeZone(ZoneId.of("GMT+13:45")), new Timezone("GMT+13:45").getJavaTimeZone());
        assertEquals(TimeZone.getTimeZone(ZoneId.of("GMT-09:30")), new Timezone("GMT-09:30").getJavaTimeZone());
        assertEquals(TimeZone.getTimeZone(ZoneId.of("GMT+00:00")), new Timezone("GMT+00:00").getJavaTimeZone());
    }

    @Test
    public void hashCode_test() {
        assertEquals(new Timezone("GMT+14:00").hashCode(), new Timezone("GMT+14:00").hashCode());
        assertEquals(new Timezone("GMT-12:00").hashCode(), new Timezone("GMT-12:00").hashCode());
        assertEquals(new Timezone("GMT+13:45").hashCode(), new Timezone("GMT+13:45").hashCode());
        assertEquals(new Timezone("GMT-09:30").hashCode(), new Timezone("GMT-09:30").hashCode());
        assertEquals(new Timezone("GMT+00:00").hashCode(), new Timezone("GMT+00:00").hashCode());


        assertNotEquals(new Timezone("GMT+14:00").hashCode(), new Timezone("GMT-12:00").hashCode());
        assertNotEquals(new Timezone("GMT+13:45").hashCode(), new Timezone("GMT-09:30").hashCode());
        assertNotEquals(new Timezone("GMT+00:00").hashCode(), new Timezone("GMT+14:00").hashCode());
    }
}
