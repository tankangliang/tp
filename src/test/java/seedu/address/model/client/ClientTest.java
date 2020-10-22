package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Client client = new ClientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    }

    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(ALICE.isSameClient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameClient(null));

        // different phone and email -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));

        // different name -> returns false
        editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCountry(VALID_COUNTRY_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameClient(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCountry(VALID_COUNTRY_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameClient(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .withCountry(VALID_COUNTRY_BOB).build();
        assertTrue(ALICE.isSameClient(editedAlice));

        // TODO: Add tests with modified notes
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client aliceCopy = new ClientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different client -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different country -> returns false
        editedAlice = new ClientBuilder(ALICE).withCountry(VALID_COUNTRY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // TODO: Add tests with modified notes
    }

    @Test
    public void hashCode_test() {
        // same values -> hashCode is same
        Client aliceCopy = new ClientBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());

        // same object -> hashCode is same
        assertEquals(ALICE.hashCode(), ALICE.hashCode());

        // different client -> hashCode is different
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());

        // different name -> hashCode is different
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different phone -> hashCode is different
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different email -> hashCode is different
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different address -> hashCode is different
        editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different country -> hashCode is different
        editedAlice = new ClientBuilder(ALICE).withCountry(VALID_COUNTRY_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different tags -> hashCode is different
        editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // TODO: Add tests with modified notes
    }
}
