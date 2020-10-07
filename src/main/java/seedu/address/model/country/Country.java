package seedu.address.model.country;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.note.Note;

public enum Country {
    SINGAPORE("Singapore", "SG", new ArrayList<>());

    private String countryName;
    private String countryCode;
    private ArrayList<Note> countryNotes;

    Country(String countryName, String countryCode, ArrayList<Note> countryNotes) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryNotes = countryNotes;
    }

    public static Country countryCodeToCountry(String countryCode) {
        for (Country c : Country.values()) {
            System.out.println(c);
        }
        return null;
    }

    public List<Note> getCountryNotes() {
        return Collections.unmodifiableList(this.countryNotes);
    }

    public void addCountryNote(Note countryNote) {
        requireNonNull(countryNote);
        this.countryNotes.add(countryNote);
    }

}
