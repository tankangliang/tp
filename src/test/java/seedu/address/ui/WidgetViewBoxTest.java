package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

import guitests.guihandles.WidgetViewBoxHandle;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;
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
        widgetViewBox.updateClientDisplay(AMY);
        AMY.addClientNote(new Note("Birthday TMR"));
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
        WidgetViewBox widgetViewBox = WidgetViewBox.init();

        uiPartExtension.setUiPart(widgetViewBox);

        // check if the country is correct
        guiRobot.pauseForHuman();
    }

    @Test
    public void updateContentToEmptyObject_updatesProperly_newObjectIsSuccessfullyUpdated() {
        // This is primarily testing that the textclock does not show itself during an update.
        WidgetViewBox widgetViewBox = WidgetViewBox.init();
        widgetViewBox.updateClientDisplay(AMY);
        Client benson = TypicalClients.BENSON;
        widgetViewBox.updateClientDisplay(benson);
        uiPartExtension.setUiPart(widgetViewBox);

        assertViewBoxDisplay(widgetViewBox, benson);
    }

    @Test
    public void equals() {
        WidgetViewBox obj1 = WidgetViewBox.init();
        obj1.updateClientDisplay(AMY);
        WidgetViewBox obj2 = WidgetViewBox.init();
        obj2.updateClientDisplay(AMY);
        WidgetViewBox obj3 = WidgetViewBox.init();
        obj3.updateClientDisplay(TypicalClients.BENSON);

        // basic equals tests
        basicEqualsTests(obj1);

        // same values -> returns true
        assertTrue(obj1.equals(obj2));

        // different values -> returns false
        assertFalse(obj1.equals(obj3));
    }

    private void assertViewBoxDisplay(WidgetViewBox widgetViewBox , Client expectedClient) {
        guiRobot.pauseForHuman();

        WidgetViewBoxHandle widgetViewBoxHandle = new WidgetViewBoxHandle(widgetViewBox.getRoot());

        // Testing using the equals method of handler.
        assertTrue(widgetViewBoxHandle.equals(expectedClient));
    }

}
