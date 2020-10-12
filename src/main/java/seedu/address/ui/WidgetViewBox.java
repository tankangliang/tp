package seedu.address.ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    @FXML
    private Label textFour;
    @FXML
    private Label footer;
    private WidgetObject widgetObject;
    private Timeline textClock;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox() {
        super(FXML);
        this.textClock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.now();
            header.setText(localTime.format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        textClock.setCycleCount(Animation.INDEFINITE);
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
    }


    /**
     * Updates the current content of the widget view box to the given content.
     *
     * @param other The new content.
     */
    public void update(WidgetObject other) {
        this.widgetObject = other;
        textClock.pause();
        header.setText(other.header());
        divOne.setText(other.divOne());
        textOne.setText(other.textOne());
        divTwo.setText(other.divTwo());
        textTwo.setText(other.textTwo());
        textThree.setText(other.textThree());
        divThree.setText(other.divThree());
        textFour.setText(other.textFour());
        footer.setText(other.footer());
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
