package seedu.address.model.widget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class WidgetObjectTest {

    @Test
    void testDisplayedFieldsProperTruncateWhenOverLimit() {
        WidgetObject wo = new WidgetObject();
        wo.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        assertEquals(wo.footer(), "13");
    }

    @Test
    void testEquals() {
        WidgetObject o1 = new WidgetObject();
        o1.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        WidgetObject o2 = new WidgetObject();
        o2.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
        assertEquals(o1, o2);
    }

    @Test
    void testNotEquals() {
        WidgetObject o1 = new WidgetObject();
        o1.set("1");
        WidgetObject o2 = new WidgetObject();
        o2.set("1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        assertNotEquals(o1, o2);
    }
}
