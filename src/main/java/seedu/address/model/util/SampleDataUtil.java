package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ContractExpiryDate;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Timezone;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Country("SG"), new Timezone("GMT+8"),
                new ContractExpiryDate("21-4-2022"), getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Country("SG"),
                new Timezone("GMT+8"), new ContractExpiryDate("12-12-2021"),
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Country("SG"), new Timezone("GMT+8"),
                new ContractExpiryDate("1-4-2023"), getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Country("SG"),
                new Timezone("GMT+8"), new ContractExpiryDate("23-12-2020"),
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Country("SG"), new Timezone("GMT+8"),
                new ContractExpiryDate("3-2-2021"), getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Country("SG"), new Timezone("GMT+8"),
                new ContractExpiryDate("9-6-2024"), getTagSet("colleagues"))
        };
    }

    public static ArrayList<CountryNote> getSampleCountryNotes() {
        return new ArrayList<>(Arrays.asList(
            new CountryNote("This is a country note", new Country("SG")),
            new CountryNote("This is anotehr country note", new Country("MY"))
        ));
    }

    public static ArrayList<Note> getSampleClientNotes() {
        return new ArrayList<>(Arrays.asList(
            new Note("This is a regular note"),
            new Note("This is anotehr regular note")
        ));
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }

        //TODO: have method to add client notes to AddressBook
        for (Note note: getSampleClientNotes()) {
        }

        for (CountryNote countryNote : getSampleCountryNotes()) {
            sampleAb.addCountryNote(countryNote);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
