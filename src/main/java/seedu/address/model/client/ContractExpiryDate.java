package seedu.address.model.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents a contract expiry date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class ContractExpiryDate implements Comparable<ContractExpiryDate> {

    public static final String MESSAGE_CONSTRAINTS = "ContractExpiryDate should be in the format day-month-year,"
            + " where day is between 1 and 31 and month is between 1-12.";
    /*
     * ContractExpiryDate should be in the format day-month-year separating the digits.
     * Day should be in the range 1-31, month should be in the range 1-12, and year can be from 0000-9999.
     */
    public static final String VALIDATION_REGEX =
            "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]|[1][0-2])[-]([0-9]{4}|[0-9]{2})$";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String DEFAULT_DATE_FORMAT = "d-M-uuuu";
    public final String value;
    private final LocalDate date;

    /**
     * Constructs a date object.
     */
    public ContractExpiryDate(LocalDate date) {
        this.date = date;
        this.value = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    /**
     * Returns true if the String follows the correct date format.
     * This does not check for valid dates (e.g. 31st Feb will return true).
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContractExpiryDate // instanceof handles nulls
                && date.equals(((ContractExpiryDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(ContractExpiryDate other) {
        return date.compareTo(other.date);
    }
}
