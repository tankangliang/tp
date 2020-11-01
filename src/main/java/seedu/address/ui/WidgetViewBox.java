package seedu.address.ui;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;

/**
 * An Ui component that displays the information of {@code Client}.
 */
public class WidgetViewBox extends UiPart<Region> {

    private static final String FXML = "WidgetViewBox.fxml";

    private final ObservableList<Client> clientObservableList;
    private int displayedClientIndex = -1;
    private int clientListSize = -1;
    private Client displayedClient = null;
    private TextClock textClock;

    @FXML
    private ScrollPane widgetScrollPane;
    @FXML
    private VBox viewBox;
    @FXML
    private VBox tbmLogoContainer;
    @FXML
    private ImageView tbmLogo;
    @FXML
    private Text timer;
    @FXML
    private Text name;
    @FXML
    private Text country;
    @FXML
    private Text phone;
    @FXML
    private Text email;
    @FXML
    private Text address;
    @FXML
    private Text contractExpiryDate;
    @FXML
    private Text noteTitle;
    @FXML
    private VBox clientNoteListView;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox(ObservableList<Client> clientObservableList) {
        super(FXML);
        this.clientObservableList = clientObservableList;
        clientListSize = clientObservableList.size();
        clientObservableList.addListener((ListChangeListener<Client>) c -> {
            if (c.next()) {
                if (clientObservableList.size() == clientListSize && !c.wasPermutated()) {
                    if (displayedClientIndex != -1) {
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
                        setToDefaultView();
                    }
                }
            }
        });
        setToDefaultView();
    }

    /**
     * Updates the current client of the widget view box to the given client.
     *
     * @param client The new client to display.
     */
    public void updateClientDisplay(Client client) {
        widgetScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        textClock.pause();
        tbmLogoContainer.getChildren().clear();
        tbmLogoContainer.setStyle("-fx-padding: 0 0 0 0; -fx-spacing: 0;");
        displayedClient = client;
        for (int i = 0; i < clientObservableList.size(); i++) {
            if (clientObservableList.get(i).isSameClient(client)) {
                displayedClient = clientObservableList.get(i);
                displayedClientIndex = i;
            }
        }
        country.setText(client.getCountry().getCountryName() + " (" + client.getTimezone().toString() + ")");
        name.setText(client.getName().toString());
        phone.setText(client.getPhone().toString());
        email.setText(client.getEmail().toString());
        address.setText(client.getAddress().toString());
        contractExpiryDate.setText("Expiry: " + client.getContractExpiryDate().displayValue);
        noteTitle.setText("Notes");
        updateClientNotesDisplay(client.getClientNotesAsUnmodifiableList());
        drawPaneBorder();
    }

    /**client
     * Static factory for use in GUI testing.
     *
     * @return WidgetViewBox
     */
    public static WidgetViewBox init() {
        return new WidgetViewBox(FXCollections.emptyObservableList());
    }

    /**
     * Sets the display to the default view for the view box.
     */
    public void setToDefaultView() {
        //TODO: Some better information
        widgetScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tbmLogoContainer.getChildren().clear();
        tbmLogoContainer.getChildren().add(tbmLogo);
        tbmLogoContainer.getChildren().add(timer);
        tbmLogoContainer.setStyle("-fx-padding: 20 0 25 0; -fx-spacing: 10");
        textClock = new TextClock(timer);
        textClock.play();
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        int offset = TimeZone.getTimeZone(ZoneId.systemDefault()).getOffset(new Date().getTime()) / 1000 / 60 / 60;
        String offsetString = (offset < 0 ? "-" : "+") + offset;
        country.setText(Locale.getDefault().getDisplayCountry() + " (GMT" + offsetString + ")");
        contractExpiryDate.setText("");
        noteTitle.setText("");
        clientNoteListView.getChildren().clear();
        clearPaneBorder();
        displayedClient = null;
        displayedClientIndex = -1;
    }

    /**
     * Updates the displayed notes.
     *
     * @param clientNotes The client notes to display.
     */
    private void updateClientNotesDisplay(List<Note> clientNotes) {
        clientNoteListView.getChildren().clear();
        int noteIdx = 1;
        for (Note note : clientNotes) {
            NoteListCard noteListCard = new NoteListCard(note, noteIdx++);
            clientNoteListView.getChildren().add(noteListCard.getRoot());
        }
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
        clientNoteListView.setStyle("-fx-border-color: #FF3333; -fx-border-radius: 10; -fx-border-width: 2;"
                + " -fx-padding: 10");
    }

    private void clearPaneBorder() {
        clientNoteListView.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-padding: 0");
    }

}
