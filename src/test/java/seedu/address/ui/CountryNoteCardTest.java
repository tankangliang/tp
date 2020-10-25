package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guitests.guihandles.CountryNoteCardHandle;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

public class CountryNoteCardTest extends GuiUnitTest {
    private static final String DUMMY_NOTE_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"
            + " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"
            + " exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"
            + " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint"
            + " occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private final Country country = new Country("SG");

    @Test
    public void display() {
        CountryNote countryNote = new CountryNote(DUMMY_NOTE_CONTENT, country);
        CountryNoteCard countryNoteCard = new CountryNoteCard(1, countryNote);
        uiPartExtension.setUiPart(countryNoteCard);
        assertCardDisplay(countryNoteCard, countryNote, 1);
    }

    private void assertCardDisplay(CountryNoteCard countryNoteCard, CountryNote expectedCard, int expectedId) {
        guiRobot.pauseForHuman();

        CountryNoteCardHandle countryNoteCardHandle = new CountryNoteCardHandle(countryNoteCard.getRoot());
        assertEquals(expectedId + ". " + expectedCard.getNoteContents() + " [Singapore (SG)]",
                countryNoteCardHandle.getCountryNoteContent());
    }
}
