package seedu.address.ui;

import java.util.Locale;
import java.util.TimeZone;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.widget.WidgetObject;

/**
 * An Ui component that displays the information of {@code WidgetObject}.
 */
public class WidgetViewBox extends UiPart<Region> {
    private static final String FXML = "WidgetViewBox.fxml";
    @FXML
    private VBox viewBox;
    @FXML
    private Label header;
    @FXML
    private Label divOne;
    @FXML
    private Label textOne;
    @FXML
    private Label divTwo;
    @FXML
    private Label textTwo;
    @FXML
    private Label textThree;
    @FXML
    private Label divThree;
    //TODO: This is a stop gap to display everything about the client's notes.
    @FXML
    private TextArea textFour;
    @FXML
    private Label footer;
    private WidgetObject widgetObject;
    private TextClock textClock;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox() {
        super(FXML);
        textClock = new TextClock(header);
        textClock.play();
        divOne.setText(Locale.getDefault().getDisplayCountry());
        textOne.setText(TimeZone.getDefault().getDisplayName());
        divTwo.setText("Travelling BusinessMan");
        textTwo.setText("");
        textThree.setText("");
        divThree.setText("");
        textFour.setText("");
        footer.setText("Made in NUS");
    }

    /**
     * Private constructor for testing purposes.
     *
     * @param object Widget Object to be used in testing.
     */
    private WidgetViewBox(WidgetObject object) {
        super(FXML);
        this.widgetObject = object;
        textClock = new TextClock(header);
        header.setText(object.getHeader());
        divOne.setText(object.getDivOne());
        textOne.setText(object.getTextOne());
        divTwo.setText(object.getDivTwo());
        textTwo.setText(object.getTextTwo());
        textThree.setText(object.getTextThree());
        divThree.setText(object.getDivThree());
        textFour.setText(object.getTextFour());
        footer.setText(object.getFooter());
    }

    /**
     * Updates the current content of the widget view box to the given content.
     *
     * @param other The new content.
     */
    public void update(WidgetObject other) {
        this.widgetObject = other;
        textClock.pause();
        header.setText(other.getHeader());
        divOne.setText(other.getDivOne());
        textOne.setText(other.getTextOne());
        divTwo.setText(other.getDivTwo());
        textTwo.setText(other.getTextTwo());
        textThree.setText(other.getTextThree());
        divThree.setText(other.getDivThree());
        textFour.setText(other.getTextFour());
        footer.setText(other.getFooter());
    }

    /**
     * Initialiser to bypass static initialising problem in Non-FXML testing of this class.
     *
     * @param object Any Widget Object to be used in testing.
     * @return WidgeViewBox.
     */
    public static WidgetViewBox init(WidgetObject object) {
        return new WidgetViewBox(object);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WidgetViewBox)) {
            return false;
        }

        // state check
        WidgetViewBox other1 = (WidgetViewBox) other;
        return widgetObject.equals(other1.widgetObject);
    }

}
