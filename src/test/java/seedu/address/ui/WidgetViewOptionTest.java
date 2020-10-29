package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;

public class WidgetViewOptionTest {

    @Test
    public void generateClientWidgetOption_isClientTrue() {
        WidgetViewOption widgetViewOption = WidgetViewOption.generateClientWidgetOption();
        assertTrue(widgetViewOption.isClient());
        assertFalse(widgetViewOption.isCountryNote());
        assertEquals(Country.NULL_COUNTRY, widgetViewOption.getCountry());
    }

    @Test
    public void generateCountryNoteWidgetOption_isCountryNoteTrue() {
        WidgetViewOption widgetViewOption = WidgetViewOption.generateCountryNoteWidgetOption(Country.NULL_COUNTRY);
        assertFalse(widgetViewOption.isClient());
        assertTrue(widgetViewOption.isCountryNote());
        assertEquals(Country.NULL_COUNTRY, widgetViewOption.getCountry());
        widgetViewOption = WidgetViewOption.generateCountryNoteWidgetOption(new Country("SG"));
        assertFalse(widgetViewOption.isClient());
        assertTrue(widgetViewOption.isCountryNote());
        assertEquals(new Country("SG"), widgetViewOption.getCountry());
    }

    @Test
    public void generateNullWidgetOption_allFalse() {
        WidgetViewOption widgetViewOption = WidgetViewOption.generateNullWidgetOption();
        assertFalse(widgetViewOption.isClient());
        assertFalse(widgetViewOption.isCountryNote());
        assertEquals(Country.NULL_COUNTRY, widgetViewOption.getCountry());
    }

}
