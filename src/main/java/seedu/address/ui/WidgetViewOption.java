package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import seedu.address.model.country.Country;

/**
 * Represents the display option for the Widget View.
 */
public class WidgetViewOption {
    private static final String CLIENT = "CLIENT";
    private static final String COUNTRY_NOTE = "COUNTRY_NOTE";
    private static final String NONE = "NONE";

    private final boolean isClient;
    private final boolean isCountryNote;
    private final Country country;

    /**
     * Initializes a WidgetViewOption.
     *
     * @param isClient True if displaying client view.
     * @param isCountryNote True if displaying country notes view.
     * @param country The country of the country notes to view.
     */
    private WidgetViewOption(boolean isClient, boolean isCountryNote, Country country) {
        assert !(isClient && isCountryNote) : "isClient and isCountryNote cannot be both true";
        requireNonNull(country);
        assert !(isClient && isCountryNote); // isClient and isCountryNote cannot be both true
        this.isClient = isClient;
        this.isCountryNote = isCountryNote;
        this.country = country;
    }

    /**
     * Generates a client widget option that represents intent to view client.
     *
     * @return A client widget option that represents intent to view client.
     */
    public static WidgetViewOption generateClientWidgetOption() {
        return new WidgetViewOption(true, false, Country.NULL_COUNTRY);
    }

    /**
     * Generates a country note widget option that represents intent to view country notes.
     *
     * @return A country note widget option that represents intent to view country notes.
     */
    public static WidgetViewOption generateCountryNoteWidgetOption(Country country) {
        return new WidgetViewOption(false, true, country);
    }

    /**
     * Generates a null widget option.
     *
     * @return A null widget option.
     */
    public static WidgetViewOption generateNullWidgetOption() {
        return new WidgetViewOption(false, false, Country.NULL_COUNTRY);
    }

    /**
     * Returns whether UI should display client view.
     *
     * @return True if UI should display client view.
     */
    public boolean isClient() {
        return isClient;
    }

    /**
     * Returns whether UI should display country notes view.
     *
     * @return True if UI should display country notes view.
     */
    public boolean isCountryNote() {
        return isCountryNote;
    }

    /**
     * Returns the country of the country notes.
     *
     * @return The country of the country notes.
     */
    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        if (isClient) {
            return CLIENT;
        } else if (isCountryNote) {
            return COUNTRY_NOTE;
        } else {
            return NONE;
        }
    }
}
