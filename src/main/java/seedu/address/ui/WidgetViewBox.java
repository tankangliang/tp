package seedu.address.ui;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;

/**
 * An Ui component that displays the information of {@code Client}.
 */
public class WidgetViewBox extends UiPart<Region> {
    private static Logger logger = LogsCenter.getLogger(WidgetViewBox.class);
    private static final String FXML = "WidgetViewBox.fxml";
    @FXML
    private VBox viewBox;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label country;
    @FXML
    private Label contractExpiryDate;
    @FXML
    private Label noteTitle;
    @FXML
    private ScrollPane clientNoteScrollPane;
    @FXML
    private VBox clientNoteListView;
    private final ObservableList<Client> clientObservableList;
    private int displayedClientIndex;
    private Client displayedClient;
    private TextClock textClock;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox(ObservableList<Client> clientObservableList) {
        super(FXML);
        this.clientObservableList = clientObservableList;

        clientObservableList.addListener((ListChangeListener<Client>) c -> {
            if (c.next()) {
                if (c.wasUpdated() || c.wasReplaced()) {
                    updateClientDisplay(clientObservableList.get(displayedClientIndex));
                } else {
                    for (int i = 0; i < clientObservableList.size(); i++) {
                        Client client = clientObservableList.get(i);
                        if (client.isSameClient(displayedClient)) {
                            displayedClient = client;
                            displayedClientIndex = i;
                            return;
                        }
                    }
                }
            }
        });
        initDefault();
    }

    /**
     * Updates the current client of the widget view box to the given client.
     *
     * @param client The new client to display.
     */
    public void updateClientDisplay(Client client) {
        textClock.pause();
        displayedClient = client;
        for (int i = 0; i < clientObservableList.size(); i++) {
            if (clientObservableList.get(i).isSameClient(client)) {
                displayedClient = clientObservableList.get(i);
                displayedClientIndex = i;
            }
        }
        name.setText(client.getName().toString());
        phone.setText(client.getPhone().toString());
        email.setText(client.getEmail().toString());
        country.setText(client.getCountry().getCountryName());
        contractExpiryDate.setText("Expiry: " + client.getContractExpiryDate().displayValue);
        noteTitle.setText("Notes");

        Platform.runLater(() -> updateClientNotesDisplay(client.getClientNotes()));
        drawPaneBorder();
    }

    /**
     * Static factory for use in GUI testing.
     *
     * @return WidgetViewBox
     */
    public static WidgetViewBox init() {
        return new WidgetViewBox(FXCollections.emptyObservableList());
    }

    /**
     * Sets a default view for the view box.
     */
    private void initDefault() {
        //TODO: Some better information
        textClock = new TextClock(name);
        country.setText(Locale.getDefault().getDisplayCountry());
        textClock.play();
    }

    /**
     * Updates the displayed notes.
     *
     * @param clientNotes The client notes to display.
     */
    private void updateClientNotesDisplay(List<Note> clientNotes) {
        clearChildren();
        int noteIdx = 1;
        for (Note note : clientNotes) {
            NoteListCard noteListCard = new NoteListCard(note, noteIdx++);
            clientNoteListView.getChildren().add(noteListCard.getRoot());
        }
    }

    /**
     * Per fn name.
     */
    private void clearChildren() {
        clientNoteListView.getChildren().clear();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WidgetViewBox)) {
            return false;
        }

        // state check
        Client other1 = ((WidgetViewBox) other).displayedClient;
        return displayedClient.equals(other1);
    }

    private void drawPaneBorder() {
        clientNoteScrollPane.setStyle("-fx-border-color: #FF3333; -fx-border-radius: 5; -fx-border-width: 2;");
        clientNoteListView.setPadding(new Insets(5, 5, 5, 10));
    }

}
