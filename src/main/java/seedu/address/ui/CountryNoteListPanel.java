package seedu.address.ui;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

/**
 * Panel containing the list of country notes.
 */
public class CountryNoteListPanel extends UiPart<Region> {

    private static final String FXML = "CountryNoteListPanel.fxml";
    private static final String HEADER_ALL_COUNTRIES_TEXT = "All Country Notes";

    private boolean displayAllCountries = true;
    private ObservableList<CountryNote> countryNoteObservableList;

    @FXML
    private Label header;
    @FXML
    private VBox countryNoteListView;

    /**
     * Initializes a {@code CountryNoteListPanel} with a countryNoteObservableList.
     */
    public CountryNoteListPanel(ObservableList<CountryNote> countryNoteObservableList) {
        super(FXML);
        this.countryNoteObservableList = countryNoteObservableList;
        header.setText("Country Notes");

        updateCountryNoteListView(countryNoteObservableList);
        countryNoteObservableList.addListener(new ListChangeListener<CountryNote>() {
            @Override
            public void onChanged(Change<? extends CountryNote> c) {
                if (c.next()) {
                    updateCountryNoteListView(countryNoteObservableList);
                }
            }
        });
    }

    private void updateCountryNoteListView(ObservableList<CountryNote> countryNoteObservableList) {
        countryNoteListView.getChildren().clear();
        // This index is used to keep track of how many country notes have been displayed so far.
        int startIndex = 0;
        if (!displayAllCountries) {
            initCountryNoteListViewFromCountryNotes(countryNoteListView, countryNoteObservableList, startIndex);
            return;
        }

        Country currCountry = null;
        List<CountryNote> countryNotes = new LinkedList<>();
        for (CountryNote countryNote : countryNoteObservableList) {
            if (currCountry == null) {
                currCountry = countryNote.getCountry();
            }
            if (!currCountry.equals(countryNote.getCountry())) {
                ObservableList<CountryNote> subCountryNoteObservableList = FXCollections.observableList(countryNotes);
                countryNoteListView.getChildren()
                        .add(new CountryNoteListSubPanel(currCountry, subCountryNoteObservableList, startIndex)
                                .getRoot());
                startIndex += subCountryNoteObservableList.size();
                currCountry = countryNote.getCountry();
                countryNotes.clear();
            }
            countryNotes.add(countryNote);
        }
        if (countryNotes.size() > 0) {
            ObservableList<CountryNote> subCountryNoteObservableList = FXCollections.observableList(countryNotes);
            countryNoteListView.getChildren()
                    .add(new CountryNoteListSubPanel(currCountry, subCountryNoteObservableList, startIndex)
                            .getRoot());
        }
    }

    /**
     * Given a list of country notes and the VBox to display them in, adds the country notes to the viewbox.
     */
    private static void initCountryNoteListViewFromCountryNotes(VBox countryNoteListView,
            ObservableList<CountryNote> countryNoteObservableList, int startIndex) {
        for (int i = 0; i < countryNoteObservableList.size(); i++) {
            CountryNote countryNote = countryNoteObservableList.get(i);
            Node node = new NoteListCard(countryNote, i + startIndex + 1).getRoot();
            countryNoteListView.getChildren().add(node);
        }
    }

    /**
     * Sets the header of the CountryNoteListPanel.
     *
     * @param country The country to be displayed.
     */
    public void setHeader(Country country) {
        if (country.equals(Country.NULL_COUNTRY)) {
            countryNoteListView.setSpacing(10.0);
            header.setText(HEADER_ALL_COUNTRIES_TEXT);
            displayAllCountries = true;
        } else {
            countryNoteListView.setSpacing(20.0);
            header.setText(country + " notes");
            displayAllCountries = false;
        }
        updateCountryNoteListView(countryNoteObservableList);
    }

    /**
     * Sub-panel which is used for viewing all countries' notes.
     */
    private static class CountryNoteListSubPanel extends UiPart<Region> {
        private static final String FXML = "CountryNoteListPanel.fxml";

        @FXML
        private VBox countryNoteListContainer;
        @FXML
        private Label header;
        @FXML
        private ScrollPane countryNoteSrollPane;
        @FXML
        private VBox countryNoteListView;

        /**
         * Initializes a {@code CountryNoteListPanel} with a countryNoteObservableList.
         */
        public CountryNoteListSubPanel(Country country, ObservableList<CountryNote> countryNoteObservableList,
                int startIndex) {
            super(FXML);
            countryNoteListContainer.setStyle("-fx-border-color: #FF3333; -fx-border-radius: 20");
            header.setText(country + " notes");
            countryNoteSrollPane.setMinHeight(30.0);
            initCountryNoteListViewFromCountryNotes(countryNoteListView, countryNoteObservableList, startIndex);
        }
    }

}
