package seedu.address.model.widget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WidgetObjectTest {

    @Test
    void test_displayed_fields_proper_truncate_when_over_limit() {
        WidgetObject wo = new WidgetObject();
        wo.set("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16");
        assertEquals(wo.footer().orElse(""), "13");
    }

    @Test
    void testEquals() {
    }
}
