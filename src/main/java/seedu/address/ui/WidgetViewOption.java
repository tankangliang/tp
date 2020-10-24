package seedu.address.ui;

import seedu.address.model.country.Country;

/**
 * Represents the display option for the Widget View.
 */
public class WidgetViewOption {
    private final boolean isClient;
    private final boolean isCountryNote;
    private final Country country;

    private WidgetViewOption(boolean isClient, boolean isCountryNote, Country country) {
        assert !(isClient && isCountryNote); // isClient and isCountryNote cannot be both true
        this.isClient = isClient;
        this.isCountryNote = isCountryNote;
        this.country = country;
    }

    public static WidgetViewOption generateClientWidgetOption() {
        return new WidgetViewOption(true, false, null);
    }

    public static WidgetViewOption generateCountryNoteWidgetOption(Country country) {
        return new WidgetViewOption(false, true, country);
    }

    public static WidgetViewOption generateNullWidgetOption() {
        return new WidgetViewOption(false, false, null);
    }

    public boolean isClient() {
        return isClient;
    }

    public boolean isCountryNote() {
        return isCountryNote;
    }

    public Country getCountry() {
        return country;
    }
}
