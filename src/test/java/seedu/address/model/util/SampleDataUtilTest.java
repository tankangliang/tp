package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;
import static seedu.address.model.util.SampleDataUtil.getSampleClients;
import static seedu.address.model.util.SampleDataUtil.getTagSet;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSampleAddressBook_getSampleClients_containSameClients() {
        ReadOnlyAddressBook addressBook = getSampleAddressBook();
        Client[] sampleClientArray = getSampleClients();
        ObservableList<Client> sampleClients = FXCollections.observableList(Arrays.asList(sampleClientArray));
        assertEquals(sampleClients, addressBook.getClientList());
    }

    @Test
    public void getTagSet_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> getTagSet((String) null));
    }

    @Test
    public void getTagSet_tagStrings_returnsCorrectTags() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Tag1"));
        tags.add(new Tag("Tag2"));
        assertEquals(tags, getTagSet("Tag1", "Tag2"));
    }

}
