package seedu.address.model.widget;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class WidgetObjectTest {

    @Test
    void set_overLimit_properTruncate() {
        WidgetObject wo = new WidgetObject();
        wo.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");

        assertEquals(wo.footer(), "8");
    }

    @Test
    public void equals_sameObjectDueToTruncate_equal() {
        WidgetObject o1 = new WidgetObject();
        o1.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        WidgetObject o2 = new WidgetObject();
        o2.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
        assertEquals(o1, o2);
    }

    @Test
    public void equals_differentObjectDueToLackOfFields_notEquals() {
        WidgetObject o1 = new WidgetObject();
        o1.set("1");
        WidgetObject o2 = new WidgetObject();
        o2.set("1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        assertNotEquals(o1, o2);
    }

    @Test
    public void fieldsGetter_testSameObject_equals() {
        WidgetObject o1 = new WidgetObject();
        o1.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        WidgetObject o2 = new WidgetObject();
        o2.set("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        assertAll("Fields Equality", () -> assertEquals(o1.header(), o2.header()), () ->
                        assertEquals(o1.divOne(), o2.divOne()), () ->
                        assertEquals(o1.textOne(), o2.textOne()), () ->
                        assertEquals(o1.divTwo(), o2.divTwo()), () ->
                        assertEquals(o1.textTwo(), o2.textTwo()), () ->
                        assertEquals(o1.textThree(), o2.textThree()), () ->
                        assertEquals(o1.divThree(), o2.divThree()), () ->
                        assertEquals(o1.textFour(), o2.textFour()), () ->
                assertEquals(o1.footer(), o2.footer()));
    }
}
