package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.HelpWindow.USERGUIDE_URL;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxToolkit;

import guitests.guihandles.HelpWindowHandle;
import guitests.guihandles.MainWindowHandle;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
/**
 * This test class does not conduct a unit test.
 */
public class MainWindowTest extends GuiUnitTest {
    // TODO: Simulate full user interaction with TBM
    @TempDir
    public Path temporaryFolder;
    private MainWindow mainWindow;
    private MainWindowHandle mainWindowHandle;
    private Stage stage;

    @BeforeEach
    public void setup() throws Exception {
        JsonAddressBookStorage jsonAddressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(jsonAddressBookStorage, jsonUserPrefsStorage);
        FxToolkit.setupStage(stage -> {
            this.stage = stage;
            mainWindow = new MainWindow(stage, new LogicManager(new ModelManager(), storageManager), new MainApp());
            mainWindow.fillInnerParts();
            mainWindowHandle = new MainWindowHandle(stage);
            mainWindowHandle.focus();
        });
        FxToolkit.showStage();
    }

    @Test
    public void main() throws Exception {

        guiRobot.pauseForHuman();
        assertTrue(mainWindowHandle.isShowing());


        // checks the interaction of copy url and url is correct
        guiRobot.clickOn("Help");
        guiRobot.clickOn("F1");
        guiRobot.pauseForHuman();
        guiRobot.clickOn("#copyButton");
        if (!guiRobot.isHeadlessMode()) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            assertEquals(USERGUIDE_URL, clipboard.getData(DataFlavor.stringFlavor));
        }
        guiRobot.pauseForHuman();
        guiRobot.press(KeyCode.ESCAPE);
        assertFalse(HelpWindowHandle.isWindowPresent());

        // TODO: Command Execution test

    }
}
