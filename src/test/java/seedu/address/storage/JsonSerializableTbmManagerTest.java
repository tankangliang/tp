package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TbmManager;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalClients;

public class JsonSerializableTbmManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTbmManagerTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsTbmManager.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientTbmManager.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientTbmManager.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableTbmManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableTbmManager.class).get();
        TbmManager tbmManagerFromFile = dataFromFile.toModelType();
        TbmManager typicalClientsTbmManager = TypicalClients.getTypicalTbmManager();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("useful"));
        typicalClientsTbmManager.addCountryNote(new CountryNote("likes to queue", new Country("SG"), tags));
        assertEquals(tbmManagerFromFile, typicalClientsTbmManager);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTbmManager dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableTbmManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableTbmManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableTbmManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTbmManager.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

}
