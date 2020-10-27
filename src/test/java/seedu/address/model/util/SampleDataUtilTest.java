package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;
import static seedu.address.model.util.SampleDataUtil.getSampleClients;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;

public class SampleDataUtilTest {

    @Test
    public void getSampleAddressBook_getSampleClients_containSameClients() {
        ReadOnlyAddressBook addressBook = getSampleAddressBook();
        Client[] sampleClientArray = getSampleClients();
        ObservableList<Client> sampleClients = FXCollections.observableList(Arrays.asList(sampleClientArray));
        assertEquals(sampleClients, addressBook.getClientList());
    }

}
