package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.note.Note;

/**
 * UI container for displaying a country note object.
 */
public class CountryNoteCard extends UiPart<Region> {

    private static final String FXML = "ClientNoteCard.fxml";

    private final Note clientNote;

    @FXML
    private Label clientNoteContent;
    @FXML
    private HBox countryNoteContainer;

    /**
     * Initializes a CountryNoteCard.
     *
     * @param index The index of the country note.
     * @param clientNote The country note to be displayed by this card.
     */
    public CountryNoteCard(int index, Note clientNote) {
        super(FXML);
        requireAllNonNull(index, clientNote);
        this.clientNote = clientNote;
        clientNoteContent.setText(index + ". " + clientNote);
    }
}
