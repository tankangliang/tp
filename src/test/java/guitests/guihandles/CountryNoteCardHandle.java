package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.note.CountryNote;

/**
 * Provides a handler for {@code CountryNoteCard}.
 */
public class CountryNoteCardHandle extends NodeHandle<Node> {
    private static final String COUNTRY_NOTE_CONTENT_LABEL_ID = "#countryNoteContent";
    private final Label countryNoteContent;

    /**
     * Constructor for handler.
     *
     * @param countryNoteCard
     */
    public CountryNoteCardHandle(Node countryNoteCard) {
        super(countryNoteCard);

        countryNoteContent = getChildNode(COUNTRY_NOTE_CONTENT_LABEL_ID);
    }

    public String getCountryNoteContent() {
        return countryNoteContent.getText();
    }

    /**
     * Returns true if this handle contains {@code client}.
     */
    public boolean equals(CountryNote countryNote) {
        return getCountryNoteContent().equals(countryNote.getNoteContents());
    }

}
