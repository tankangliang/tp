package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ContractExpiryDate;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Timezone;
import seedu.address.model.country.Country;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_COUNTRY = "SG";
    public static final String DEFAULT_TIMEZONE = "GMT+8";
    public static final String DEFAULT_CONTRACT_EXPIRY_DATE = "4-1-2021";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Country country;
    private Timezone timezone;
    private ContractExpiryDate contractExpiryDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        country = new Country(DEFAULT_COUNTRY);
        timezone = new Timezone(DEFAULT_TIMEZONE);
        try {
            contractExpiryDate = ParserUtil.parseContractExpiryDate(DEFAULT_CONTRACT_EXPIRY_DATE);
        } catch (ParseException ignored) {
            // Intentionally left empty, since if contractExpiryDate fails to initialize, other tests will fail as well.
        }
        tags = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        country = clientToCopy.getCountry();
        timezone = clientToCopy.getTimezone();
        contractExpiryDate = clientToCopy.getContractExpiryDate();
        tags = new HashSet<>(clientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Country} of the {@code Client} that we are building.
     */
    public ClientBuilder withCountry(String country) {
        this.country = new Country(country);
        return this;
    }

    /**
     * Sets the {@code Timezone} of the {@code Client} that we are building.
     */
    public ClientBuilder withTimezone(String timezone) {
        this.timezone = new Timezone(timezone);
        return this;
    }

    /**
     * Sets the {@code ContractExpiryDate} of the {@code Client} that we are building.
     */
    public ClientBuilder withContractExpiryDate(String contractExpiryDate) {
        try {
            this.contractExpiryDate = ParserUtil.parseContractExpiryDate(contractExpiryDate);
        } catch (ParseException ignored) {
            // Intentionally left empty, since if contractExpiryDate fails to initialize, other tests will fail as well.
        }
        return this;
    }

    public Client build() {
        return new Client(name, phone, email, address, country, timezone, contractExpiryDate, tags);
    }

}
