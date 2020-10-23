package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.note.CountryNote;

public class CountryNoteCard extends UiPart<Region> {

    private static final String FXML = "CountryNoteCard.fxml";

    private final CountryNote countryNote;

    @FXML
    private Label countryNoteContent;
    @FXML
    private HBox countryNoteContainer;

    public CountryNoteCard(int index, CountryNote countryNote) {
        super(FXML);
        this.countryNote = countryNote;
        countryNoteContent.setText(index + ". " + countryNote.getNoteContents());
    }

    public HBox getCountryNoteContainer() {
        return countryNoteContainer;
    }
}
