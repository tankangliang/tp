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

        assertEquals(wo.getFooter(), "8");
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
        assertAll("Fields Equality", () ->
                assertEquals(o1.getHeader(), o2.getHeader()), () ->
                assertEquals(o1.getDivOne(), o2.getDivOne()), () ->
                assertEquals(o1.getTextOne(), o2.getTextOne()), () ->
                assertEquals(o1.getDivTwo(), o2.getDivTwo()), () ->
                assertEquals(o1.getTextTwo(), o2.getTextTwo()), () ->
                assertEquals(o1.getTextThree(), o2.getTextThree()), () ->
                assertEquals(o1.getDivThree(), o2.getDivThree()), () ->
                assertEquals(o1.getTextFour(), o2.getTextFour()), () ->
                assertEquals(o1.getFooter(), o2.getFooter()));
    }
}
