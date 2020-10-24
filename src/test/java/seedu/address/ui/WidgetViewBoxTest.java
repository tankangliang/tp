package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import guitests.guihandles.WidgetViewBoxHandle;
import seedu.address.model.widget.WidgetObject;

/**
 * GUI unit test for WidgetViewBox. Test done to ensure the integrity of content displayed does not regress in future
 * refactoring.
 */
public class WidgetViewBoxTest extends GuiUnitTest {

    private static final WidgetObject TEST_OBJECT = new WidgetObject();
    private static final String FIRST_LINE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,";
    private static final String SECOND_LINE = "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private static final String THIRD_LINE = "Ut enim ad minim veniam,";
    private static final String FOURTH_LINE = "quis nostrud exercitation ullamco laboris nisi ut aliquip ex consequat.";

    private static final String FIFTH_LINE = "Duis aute irure dolor in reprehenderit in voluptate vesse cillum dolore.";

    private static final String SIXTH_LINE = "Excepteur sint occaecat cupidatat non proident,";
    private static final String SEVENTH_LINE = "sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private static final String EIGHTH_LINE = "Sed ut perspiciatis unde omnis iste error sit voluptatem accusantium.";
    private static final String NINTH_LINE = "totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et.";

    @BeforeAll
    public static void setupObject() {
        TEST_OBJECT.set(FIRST_LINE, SECOND_LINE, THIRD_LINE, FOURTH_LINE, FIFTH_LINE, SIXTH_LINE, SEVENTH_LINE,
                EIGHTH_LINE, NINTH_LINE);
    }

    @Test
    public void display() {
        WidgetViewBox widgetViewBox = WidgetViewBox.init(TEST_OBJECT);
        uiPartExtension.setUiPart(widgetViewBox);

        assertViewBoxDisplay(widgetViewBox, TEST_OBJECT);
    }

    /**
     * This test checks your time, country and timezone.
     * Since this is a manual eyeballing test. It will be skipped in headless.
     */
    @Test
    public void displayDefault() {
        /*
        * Issue was raised by @rtshkmr where his JVM has correct timezone and time but wrong country.
        * Locale.getDefault().getDisplayCountry() returns United States for him.
        */
        //assumeFalse(guiRobot.isHeadlessMode(), "Test skipped in headless mode.");
        if (!guiRobot.isHeadlessMode()) {
            WidgetViewBox widgetViewBox = new WidgetViewBox();

            uiPartExtension.setUiPart(widgetViewBox);
            guiRobot.pauseForHuman();
            guiRobot.pauseForHuman();
        } else {
            assertEquals(1, 1);
        }
    }

    @Test
    public void updateContentToEmptyObject_updatesProperly_newObjectIsSuccessfullyUpdated() {
        // This is primarily testing that the textclock does not show itself during an update.
        WidgetViewBox widgetViewBox = WidgetViewBox.init(TEST_OBJECT);
        WidgetObject newObj = new WidgetObject();
        widgetViewBox.update(newObj);
        uiPartExtension.setUiPart(widgetViewBox);

        assertViewBoxDisplay(widgetViewBox, newObj);
    }

    @Test
    public void equals() {
        WidgetViewBox obj1 = WidgetViewBox.init(TEST_OBJECT);
        WidgetViewBox obj2 = WidgetViewBox.init(TEST_OBJECT);

        assertEquals(obj1, obj2);
    }

    private void assertViewBoxDisplay(WidgetViewBox widgetViewBox , WidgetObject expectedObject) {
        guiRobot.pauseForHuman();

        WidgetViewBoxHandle widgetViewBoxHandle = new WidgetViewBoxHandle(widgetViewBox.getRoot());

        // Testing using the equals method of handler.
        if (!widgetViewBoxHandle.equals(expectedObject)) {
            fail();
        } else {
            assertEquals(1, 1);
        }
    }

}

