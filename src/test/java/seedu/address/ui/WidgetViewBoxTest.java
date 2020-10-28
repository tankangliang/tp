package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import org.junit.jupiter.api.Test;

import guitests.guihandles.WidgetViewBoxHandle;
import seedu.address.model.client.Client;
import seedu.address.testutil.TypicalClients;

/**
 * GUI unit test for WidgetViewBox. Test done to ensure the integrity of content displayed does not regress in future
 * refactoring.
 */
public class WidgetViewBoxTest extends GuiUnitTest {
    private static final Client AMY = TypicalClients.AMY;

    @Test
    public void display() {
        WidgetViewBox widgetViewBox = WidgetViewBox.init();
        widgetViewBox.update(AMY);
        uiPartExtension.setUiPart(widgetViewBox);

        assertViewBoxDisplay(widgetViewBox, AMY);
    }

    /**
     * This test checks your time, country and timezone.
     * Since this is a manual eyeballing test. It will be skipped in headless.
     */
    @Test
    public void displayDefault() {
        /*
        * A bug raised here https://bugs.java.com/bugdatabase/view_bug.do?bug_id=7082429.
        */
        assumeFalse(guiRobot.isHeadlessMode(), "Test skipped in headless mode.");
        WidgetViewBox widgetViewBox = new WidgetViewBox();

        uiPartExtension.setUiPart(widgetViewBox);

        // check if the country is correct
        guiRobot.pauseForHuman();
    }

    @Test
    public void updateContentToEmptyObject_updatesProperly_newObjectIsSuccessfullyUpdated() {
        // This is primarily testing that the textclock does not show itself during an update.
        WidgetViewBox widgetViewBox = WidgetViewBox.init();
        widgetViewBox.update(AMY);
        Client benson = TypicalClients.BENSON;
        widgetViewBox.update(benson);
        uiPartExtension.setUiPart(widgetViewBox);

        assertViewBoxDisplay(widgetViewBox, benson);
    }

    @Test
    public void equals() {
        WidgetViewBox obj1 = WidgetViewBox.init();
        obj1.update(AMY);
        WidgetViewBox obj2 = WidgetViewBox.init();
        obj2.update(AMY);

        assertEquals(obj1, obj2);
    }

    private void assertViewBoxDisplay(WidgetViewBox widgetViewBox , Client client) {
        guiRobot.pauseForHuman();

        WidgetViewBoxHandle widgetViewBoxHandle = new WidgetViewBoxHandle(widgetViewBox.getRoot());

        // Testing using the equals method of handler.
        assertTrue(widgetViewBoxHandle.equals(client));
    }

}

