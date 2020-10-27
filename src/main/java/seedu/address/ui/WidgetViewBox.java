package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;

/**
 * An Ui component that displays the information of {@code Client}.
 */
public class WidgetViewBox extends UiPart<Region> {
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
    private ListView<Note> notes;
    private Client client;
    private TextClock textClock;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox() {
        super(FXML);
        textClock = new TextClock(name);
    }

    /**
     * Private constructor for testing purposes.
     *
     * @param client To be displayed.
     */
    private WidgetViewBox(Client client) {
        super(FXML);
        this.client = client;
        textClock = new TextClock(name);
        name.setText(client.getName().toString());
        phone.setText(client.getPhone().toString());
        email.setText(client.getEmail().toString());
        country.setText(client.getCountry().getCountryName());
        contractExpiryDate.setText("Expiry: " + client.getContractExpiryDate().displayValue);
//        notes.setItems();
        notes.setCellFactory(noteListView -> new ClientNoteListViewCell());
    }

    /**
     * Updates the current content of the widget view box to the given content.
     *
     * @param client The new content.
     */
    public void update(Client client) {
        this.client = client;
        textClock.pause();
        name.setText(client.getName().toString());
        phone.setText(client.getPhone().toString());
        email.setText(client.getEmail().toString());
        country.setText(client.getCountry().getCountryName());
        contractExpiryDate.setText("Expiry: " + client.getContractExpiryDate().displayValue);
    }

    /**
     * Static factory.
     *
     * @return WidgetViewBox
     */
    public static WidgetViewBox init() {
        return new WidgetViewBox();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Client)) {
            return false;
        }

        // state check
        Client other1 = (Client) other;
        return this.equals(other1);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CountryNote} using a {@code
     * CountryNoteCard}.
     */
    class ClientNoteListViewCell extends ListCell<Note> {

        @Override
        protected void updateItem(Note clientNote, boolean empty) {
            super.updateItem(clientNote, empty);

            if (empty || clientNote == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClientNoteCard(getIndex() + 1, clientNote).getRoot());
            }
        }
    }

}
