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

        if (!hasCountryNote(countryNote)) {
            internalCountryNoteList.add(countryNote);
        }
    }

    /**
     * Returns all Country Notes as an unmodifiable List.
     *
     * @return Unmodifiable list of all country notes.
     */
    public ObservableList<CountryNote> asUnmodifiableObservableList() {
        return internalCountryNoteUnmodifiableList;
    }

    /**
     * Deletes the given country note.
     *
     * @param countryNoteToDelete The country note to be deleted.
     */
    public void deleteCountryNote(CountryNote countryNoteToDelete) {
        if (!CountryCodeVerifier.isValidCountryCode(countryNoteToDelete.getCountry().getCountryCode())) {
            assert false; // should always be a valid country
        }
        requireNonNull(countryNoteToDelete);
        assert hasCountryNote(countryNoteToDelete);

        internalCountryNoteList.remove(countryNoteToDelete);
    }
}
