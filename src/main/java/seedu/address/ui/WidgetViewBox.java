package seedu.address.ui;

import java.util.Locale;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
    private ObservableList<Note> observableListNotes;
    private Client client;
    private TextClock textClock;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox() {
        super(FXML);
        initDefault();
    }

    /**
     * Updates the current content of the widget view box to the given content.
     *
     * @param client The new content.
     */
    public void update(Client client) {
        textClock.pause();
        this.client = client;
        name.setText(client.getName().toString());
        phone.setText(client.getPhone().toString());
        email.setText(client.getEmail().toString());
        country.setText(client.getCountry().getCountryName());
        contractExpiryDate.setText("Expiry: " + client.getContractExpiryDate().displayValue);
        noteTitle.setText("Notes");
        observableListNotes = client.getObsList();
        observableListNotes.addListener(new ListChangeListener<Note>() {
            @Override
            public void onChanged(Change<? extends Note> c) {
                if (c.next()) {
                    updateObservableListNotes(observableListNotes);
                }
            }
        });
        drawPaneBorder();
        initialiseNoteList(observableListNotes);
    }

    /**
     * Static factory.
     *
     * @return WidgetViewBox
     */
    public static WidgetViewBox init() {
        return new WidgetViewBox();
    }

    /**
     * Sets a default view for the view box.
     */
    private void initDefault() {
        textClock = new TextClock(name);
        country.setText(Locale.getDefault().getDisplayCountry());
        textClock.play();
    }

    /**
     * Updates the displayed notes.
     *
     * @param observableListNotes The new client notes.
     */
    private void updateObservableListNotes(ObservableList<Note> observableListNotes) {
        clearChildren();
        int noteIdx = 0;
        for (Note note : observableListNotes) {
            NoteListCard noteListCard = new NoteListCard(note, noteIdx++);
            clientNoteListView.getChildren().add(noteListCard.getRoot());
        }
    }

    /**
     * Method to call when initialising a Client to the view box. Set content of the scrollpane.
     *
     * @param observableListNotes The list of notes of the client.
     */
    private void initialiseNoteList(ObservableList<Note> observableListNotes) {
        clearChildren();
        int noteIdx = 0;
        for (Note note : observableListNotes) {
            NoteListCard noteListCard = new NoteListCard(note, noteIdx++);
            clientNoteListView.getChildren().add(noteListCard.getRoot());
        }
        clientNoteScrollPane.setContent(clientNoteListView);
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
        Client other1 = ((WidgetViewBox) other).client;
        return client.equals(other1);
    }

    private void drawPaneBorder() {
        clientNoteScrollPane.setStyle("-fx-border-color: #FF3333; -fx-border-radius: 5; -fx-border-width: 2;");
        clientNoteListView.setPadding(new Insets(5, 0, 5, 10));
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
                setGraphic(new NoteListCard(clientNote, getIndex() + 1).getRoot());
            }
        }
    }

}
