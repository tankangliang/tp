package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

/**
 * Panel containing the list of country notes.
 */
public class CountryNoteListPanel extends UiPart<Region> {

    private static final String FXML = "CountryNoteListPanel.fxml";

    @FXML
    private Label header;
    @FXML
    private VBox countryNoteListView;

    /**
     * Initializes a {@code CountryNoteListPanel} with a countryNoteObservableList.
     */
    public CountryNoteListPanel(ObservableList<CountryNote> countryNoteObservableList) {
        super(FXML);
        header.setText("Country Notes");

        for (int i = 0; i < countryNoteObservableList.size(); i++) {
            CountryNote countryNote = countryNoteObservableList.get(i);
            Node node = new NoteListCard(countryNote, i + 1).getRoot();
            countryNoteListView.getChildren().add(node);
        }
        countryNoteObservableList.addListener(new ListChangeListener<CountryNote>() {
            @Override
            public void onChanged(Change<? extends CountryNote> c) {
                while (c.next()) {
                    countryNoteListView.getChildren().clear();
                    for (int i = 0; i < countryNoteObservableList.size(); i++) {
                        CountryNote countryNote = countryNoteObservableList.get(i);
                        Node node = new NoteListCard(countryNote, i + 1).getRoot();
                        countryNoteListView.getChildren().add(node);
                    }
                }
            }
        });
    }

    /**
     * Sets the header of the CountryNoteListPanel.
     *
     * @param country The country to be displayed.
     */
    public void setHeader(Country country) {
        if (country.equals(Country.NULL_COUNTRY)) {
            header.setText("All Country Notes");
        } else {
            header.setText(country + " notes");
        }
    }

}
