package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTRACT_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTRACT_EXPIRY_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEZONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEZONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withCountry("SG").withTimezone("GMT+8").withContractExpiryDate("1-4-2021")
            .withLastModifiedInstant("2020-01-01T00:00:00.000000Z").withTags("friends").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withCountry("SG").withTimezone("GMT+8")
            .withEmail("johnd@example.com").withPhone("98765432").withContractExpiryDate("3-12-2022")
            .withLastModifiedInstant("2020-02-02T00:00:00.000000Z").withTags("owesMoney", "friends").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withCountry("US").withTimezone("GMT-4")
            .withContractExpiryDate("30-1-2022").withLastModifiedInstant("2020-03-03T00:00:00.000000Z").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withCountry("SG")
            .withTimezone("GMT+8").withContractExpiryDate("28-2-2021")
            .withLastModifiedInstant("2020-04-04T00:00:00.000000Z").withTags("friends").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withCountry("GB")
            .withTimezone("GMT+1").withContractExpiryDate("10-10-2024")
            .withLastModifiedInstant("2020-05-05T00:00:00.000000Z").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withCountry("JP").withTimezone("GMT+9")
            .withContractExpiryDate("9-11-2022").withLastModifiedInstant("2020-06-06T00:00:00.000000Z").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withCountry("US").withTimezone("GMT-4")
            .withContractExpiryDate("2-8-2021").withLastModifiedInstant("2020-07-07T00:00:00.000000Z").build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withCountry("IN")
            .withTimezone("GMT+5").withContractExpiryDate("23-4-2021")
            .withLastModifiedInstant("2020-08-08T00:00:00.000000Z").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withCountry("US").withTimezone("GMT-4")
            .withContractExpiryDate("31-12-2020").withLastModifiedInstant("2020-09-09T00:00:00.000000Z").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withCountry(VALID_COUNTRY_AMY)
            .withTimezone(VALID_TIMEZONE_AMY).withContractExpiryDate(VALID_CONTRACT_EXPIRY_DATE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withCountry(VALID_COUNTRY_BOB)
            .withTimezone(VALID_TIMEZONE_BOB).withContractExpiryDate(VALID_CONTRACT_EXPIRY_DATE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
