package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.note.CountryNote;

/**
 * A widget that wraps around ListView.
 */
public class CountryNoteListPanel extends UiPart<Region> {
    private static final String FXML = "WidgetListViewBox.fxml";

    @FXML
    private VBox viewBox;
    @FXML
    private Label header;
    @FXML
    private ListView<CountryNote> countryNoteListView;

    /**
     * Initializes a {@code CountryNoteListPanel} with a countryNoteObservableList.
     */
    public CountryNoteListPanel(ObservableList<CountryNote> countryNoteObservableList) {
        super(FXML);
        header.setText("Country Notes");
        countryNoteListView.setItems(countryNoteObservableList);
        countryNoteListView.setCellFactory(listView -> new CountryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CountryNote} using a {@code
     * CountryNoteCard}.
     */
    class CountryListViewCell extends ListCell<CountryNote> {

        @Override
        protected void updateItem(CountryNote countryNote, boolean empty) {
            super.updateItem(countryNote, empty);

            if (empty || countryNote == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CountryNoteCard(getIndex() + 1, countryNote).getRoot());
            }
        }
    }
}
