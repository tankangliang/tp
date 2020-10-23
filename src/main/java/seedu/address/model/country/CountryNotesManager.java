package seedu.address.model.country;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.note.CountryNote;

/**
 * A high-level class responsible for mapping ISO3166 countries to countryNotes.
 */
public class CountryNotesManager {

    private final ObservableList<CountryNote> internalCountryNoteList = FXCollections.observableArrayList();
    private final ObservableList<CountryNote> internalCountryNoteUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalCountryNoteList);

    /**
     * Initializes a CountryNotesManager with a Map that maps ISO3166 2-letter country codes to countries.
     */
    public CountryNotesManager() {
    }

    /**
     * Checks if {@code countryNote} already exists.
     *
     * @param countryNote The note to be checked.
     * @return Whether {@code countryNote} already exists.
     */
    public boolean hasCountryNote(CountryNote countryNote) {
        requireNonNull(countryNote);
        return internalCountryNoteList.contains(countryNote);
    }

    /**
     * Adds the {@code countryNote}.
     *
     * @param countryNote The country note to be added.
     */
    public void addCountryNote(CountryNote countryNote) {
        if (!CountryCodeVerifier.isValidCountryCode(countryNote.getCountry().getCountryCode())) {
            assert false; // should always be a valid country
        }
        requireNonNull(countryNote);

        internalCountryNoteList.add(countryNote);
    }

    /**
     * Returns all Country Notes. Returning a list of all country notes prevents deep-copying.
     *
     * @return List of all country notes.
     */
    public ObservableList<CountryNote> asUnmodifiableObservableList() {
        return internalCountryNoteUnmodifiableList;
    }

}
