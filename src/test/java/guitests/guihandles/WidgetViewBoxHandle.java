package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;

/**
 * Provides a handler to the widget view box.
 */
public class WidgetViewBoxHandle extends NodeHandle<Node> {
    private static final String NAME_FIELD_ID = "#name";
    private static final String PHONE_FIELD_ID = "#phone";
    private static final String EMAIL_FIELD_ID = "#email";
    private static final String COUNTRY_FIELD_ID = "#country";
    private static final String CONTRACT_EXPIRY_DATE_FIELD_ID = "#contractExpiryDate";
    private static final String NOTE_TTITLE_FIELD_ID = "#noteTitle";
    private static final String NOTES_FIELD_ID = "#notes";
    private final Label nameLabel;
    private final Label phoneLabel;
    private final Label emailLabel;
    private final Label countryLabel;
    private final Label contractExpiryDateLabel;
    private final Label noteTitleLabel;
    private final ListView<Note> notesList;

    /**
     * Constructor for handler.
     *
     * @param widgetNode
     */
    public WidgetViewBoxHandle(Node widgetNode) {
        super(widgetNode);

        nameLabel = getChildNode(NAME_FIELD_ID);
        phoneLabel = getChildNode(PHONE_FIELD_ID);
        emailLabel = getChildNode(EMAIL_FIELD_ID);
        countryLabel = getChildNode(COUNTRY_FIELD_ID);
        contractExpiryDateLabel = getChildNode(CONTRACT_EXPIRY_DATE_FIELD_ID);
        noteTitleLabel = getChildNode(NOTE_TTITLE_FIELD_ID);
        notesList = getChildNode(NOTES_FIELD_ID);
    }

    /**
     * Returns true if this handle contains a {@code client}.
     */
    public boolean equals(Client client) {
        return nameLabel.getText().equals(client.getName().toString())
                && phoneLabel.getText().equals(client.getPhone().toString())
                && emailLabel.getText().equals(client.getEmail().toString())
                && countryLabel.getText().equals(client.getCountry().getCountryName())
                && contractExpiryDateLabel.getText().equals("Expiry: " + client.getContractExpiryDate().displayValue);
    }

}
