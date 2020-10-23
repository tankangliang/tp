package guitests.guihandles;

import javafx.scene.control.ListView;
import seedu.address.model.note.CountryNote;

public class CountryNoteListPanelHandle extends NodeHandle<ListView<CountryNote>>{

    public CountryNoteListPanelHandle(ListView<CountryNote> countryNoteListNode) {
        super(countryNoteListNode);
    }
}
