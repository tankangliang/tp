package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.note.CountryNote;

/**
 * UI container for displaying a country note object.
 */
public class CountryNoteCard extends UiPart<Region> {

    private static final String FXML = "CountryNoteCard.fxml";

    private final CountryNote countryNote;

    @FXML
    private Label countryNoteContent;
    @FXML
    private HBox countryNoteContainer;

    /**
     * Initializes a CountryNoteCard.
     *
     * @param index The index of the country note.
     * @param countryNote The country note to be displayed by this card.
     */
    public CountryNoteCard(int index, CountryNote countryNote) {
        super(FXML);
        requireAllNonNull(index, countryNote);
        this.countryNote = countryNote;
        countryNoteContent.setText(index + ". " + countryNote.getNoteContents());
    }
}
