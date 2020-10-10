package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-f11-4.github.io/tp/UserGuide.html";
    public static final String USERGUIDE_MESSAGE = "For more information, refer to the user guide: ";
    public static final String HELP_TITLE = "Commonly used commands";
    public static final String COMMON_COMMANDS =
            "client add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COUNTRY tz/TIMEZONE\n"
            + "client view INDEX\n"
            + "client find KEYWORD [MORE_KEYWORDS]\n"
            + "client edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COUNTRY] [tz/TIMEZONE]\n"
            + "client delete INDEX\n"
            + "client note add CLIENT_INDEX t/TAG NOTE_STRING\n"
            + "client note delete CLIENT_INDEX NOTE_INDEX\n"
            + "client note update CLIENT_INDEX NOTE_INDEX NOTE_STRING t/TAG\n"
            + "country filter [c/COUNTRY]\n"
            + "country note c/COUNTRY NOTE_STRING";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label helpTitle;

    @FXML
    private Label commonCommands;

    @FXML
    private Label userGuideMessage;

    @FXML
    private Hyperlink userGuideUrl;

    @FXML
    private Button copyButton;

    /**
     * Creates a new HelpWindow.
     *
     * @param root    Stage to use as the root of the HelpWindow.
     * @param mainApp The main application instance, used for the getHostServices() method.
     */
    public HelpWindow(Stage root, MainApp mainApp) {
        super(FXML, root);
        helpTitle.setText(HELP_TITLE);
        commonCommands.setText(COMMON_COMMANDS);
        userGuideMessage.setText(USERGUIDE_MESSAGE);
        userGuideUrl.setText(USERGUIDE_URL);
        userGuideUrl.setOnAction(t -> mainApp.getHostServices().showDocument(userGuideUrl.getText()));
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow(MainApp mainApp) {
        this(new Stage(), mainApp);
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
