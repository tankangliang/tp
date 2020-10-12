package seedu.address.model.widget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COUNTRY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COUNTRY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIMEZONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMEZONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;



import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClientAddCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.country.CountryManager;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;



class WidgetModelManagerTest {

    @Test
    void testSetContent() {
        WidgetModel widgetModelManager1 = WidgetModel.initWidget();
        WidgetModel widgetModelManager2 = WidgetModel.initWidget();

        // set content with same object -> widget object should be the same
        Client bob = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        widgetModelManager1.setContent(bob);
        widgetModelManager2.setContent(bob);

        assertEquals(widgetModelManager1.getWidgetContent(), widgetModelManager2.getWidgetContent());

        // Different object same person -> equals
        Client bob2 = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        widgetModelManager2.setContent(bob2);
        assertEquals(widgetModelManager1.getWidgetContent(), widgetModelManager2.getWidgetContent());

        Client bob3 = new ClientBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();

        widgetModelManager2.setContent(bob3);
        assertNotEquals(widgetModelManager1.getWidgetContent(), widgetModelManager2.getWidgetContent());

    }


}
