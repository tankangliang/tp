package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysClient;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ClientCardHandle;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;

public class ClientCardTest extends GuiUnitTest {

    @Test
    public void display() {

        Client client1 = new ClientBuilder().withName("client1").build();
        ClientCard clientCard = new ClientCard(client1, 1);
        uiPartExtension.setUiPart(clientCard);
        assertCardDisplay(clientCard, client1, 1);

        Client client2 = new ClientBuilder().withName("client2").build();
        clientCard = new ClientCard(client2, 2);
        uiPartExtension.setUiPart(clientCard);
        assertCardDisplay(clientCard, client2, 2);
    }

    @Test
    public void constructor_badClient_throwsException() {
        // null client
        assertThrows(NullPointerException.class, () -> new ClientCard(null, 0),
                "Card is being created with a null client");
    }

    @Test
    public void equals() {
        ClientCard amyClientCard = new ClientCard(AMY, 0);

        // same person, same index -> returns true
        ClientCard copy = new ClientCard(AMY, 0);
        assertEquals(copy, amyClientCard);

        // same object -> returns true
        assertEquals(amyClientCard, amyClientCard);

        // null -> returns false
        assertNotEquals(amyClientCard, null);

        // different types -> returns false
        assertNotEquals(amyClientCard, 0);

        // different person, same index -> returns false
        assertNotEquals(new ClientCard(BOB, 0), amyClientCard);

        // same person, different index -> returns false
        assertNotEquals(new ClientCard(AMY, 1), amyClientCard);
    }

    /**
     * Asserts that {@code clientCard} displays the details of {@code expectedClient} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ClientCard clientCard, Client expectedClient, int expectedId) {
        guiRobot.pauseForHuman();

        ClientCardHandle clientCardHandle = new ClientCardHandle(clientCard.getRoot());

        // verify id is displayed correctly
        assertEquals(expectedId + ". ", clientCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysClient(expectedClient, clientCardHandle);
    }
}
