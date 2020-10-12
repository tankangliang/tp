package seedu.address.model.widget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.model.country.Country;
import seedu.address.testutil.ClientBuilder;

public class WidgetModelManagerTest {

    @Test
    public void setContent_sameClient_isEqual() {
        WidgetModel widgetModelManager1 = WidgetModel.initWidget();
        WidgetModel widgetModelManager2 = WidgetModel.initWidget();

        // set content with same object -> widget object should be the same
        Client bob = new ClientBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();
        widgetModelManager1.setContent(bob);
        widgetModelManager2.setContent(bob);
        assertEquals(widgetModelManager1.getWidgetContent(), widgetModelManager2.getWidgetContent());


        // set content to different client objects but of the same fields and values -> meaning same person but
        // duplicate entry
        Client bob1 = new ClientBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();
        widgetModelManager2.setContent(bob1);
        assertEquals(widgetModelManager1.getWidgetContent(), widgetModelManager2.getWidgetContent());

    }

    @Test
    public void setContent_diffClient_notEqual() {
        // diff client should not be the same.
        WidgetModel widgetModelManager1 = WidgetModel.initWidget();
        WidgetModel widgetModelManager2 = WidgetModel.initWidget();
        Client bob = new ClientBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();
        Client amy = new ClientBuilder(AMY).withTags(VALID_TAG_HUSBAND).build();

        widgetModelManager1.setContent(bob);
        widgetModelManager2.setContent(amy);

        assertNotEquals(widgetModelManager1.getWidgetContent(), widgetModelManager2.getWidgetContent());
    }

    @Test
    public void setContent_notAClient_noExceptionsThrown() {
        Country country = new Country("SG");
        WidgetModelManager widgetModelManager = WidgetModelManager.initWidget();
        widgetModelManager.setContent(country);
        assertNotEquals(1, 0);
    }

}
