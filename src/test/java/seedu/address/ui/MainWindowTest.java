package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
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
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonTbmManagerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
/**
 * This test class does not conduct a unit test.
 */
public class MainWindowTest extends GuiUnitTest {
    @TempDir
    public Path temporaryFolder;
    private MainWindow mainWindow;
    private MainWindowHandle mainWindowHandle;
    private Stage stage;

    @BeforeEach
    public void setup() throws Exception {
        JsonTbmManagerStorage jsonTbmManagerStorage =
                new JsonTbmManagerStorage(temporaryFolder.resolve("tbmManager.json"));
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(jsonTbmManagerStorage, jsonUserPrefsStorage);
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
        guiRobot.clickOn("#commandTextField");
        InteractionTerminal terminal = new InteractionTerminal(guiRobot.lookup("#commandTextField")
                .queryTextInputControl());
        terminal.inputCommand("clear");

        // checks the interaction of copy url and url is correct
        guiRobot.clickOn("#help");
        guiRobot.clickOn("#helpMenuItem");
        guiRobot.pauseForHuman();
        assertTrue(guiRobot.isWindowShown(HelpWindowHandle.HELP_WINDOW_TITLE));
        guiRobot.clickOn("#copyButton");
        if (!guiRobot.isHeadlessMode()) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            assertEquals(USERGUIDE_URL, clipboard.getData(DataFlavor.stringFlavor));
        }
        guiRobot.pauseForHuman();
        guiRobot.push(KeyCode.ESCAPE);
        guiRobot.pauseForHuman();
        assertFalse(guiRobot.isWindowShown(HelpWindowHandle.HELP_WINDOW_TITLE));

        // TODO: Command Execution test
        terminal.inputCommand("client add n/Lim p/18002345 e/lim@gmail.com a/Yishun c/SG tz/GMT+8");
        terminal.inputCommand("client add n/Kim p/18002346 e/kim@gmail.com a/Kishun c/SG tz/GMT+8");
        terminal.inputCommand("client add n/Sim p/18002347 e/sim@gmail.com a/Sishun c/SG tz/GMT+8");
        terminal.inputCommand("client note add 1 t/reminder nt/birthday tmr");
        terminal.inputCommand("client note add 1 t/reminder nt/party tmr");
        terminal.inputCommand("client note add 1 t/reminder nt/takeout tmr");
        terminal.inputCommand("client note add 2 t/reminder nt/homework submission");
        terminal.inputCommand("country note add c/SG nt/shamaladingdong");
        terminal.inputCommand("country note add c/SG nt/chingchangchongdingdong");

        // bad command
        terminal.inputCommand("client viewf");

        // skips the rest if headless
        assumeFalse(guiRobot.isHeadlessMode());

        // viewing clients
        terminal.inputCommand("client view 1");
        checkLabel("#name", "Lim");
        terminal.inputCommand("client view 2");
        checkLabel("#name", "Kim");
        terminal.inputCommand("client view 3");
        checkLabel("#name", "Sim");

        // viewing country
        terminal.inputCommand("country note view");

    }

    private void checkLabel(String id, String value) {
        assertEquals(value, guiRobot.lookup(id).queryLabeled().getText());
    }

    /**
     * For gui interaction.
     */
    class InteractionTerminal {
        private TextInputControl textInputControl;
        InteractionTerminal(TextInputControl textInputControl) {
            this.textInputControl = textInputControl;
        }

        void inputCommand(String command) {
            guiRobot.clickOn("#commandTextField");
            textInputControl.setText(command);
            guiRobot.pauseForHuman();
            guiRobot.press(KeyCode.ENTER).release(KeyCode.ENTER);
            guiRobot.pauseForHuman();
        }
    }
}
