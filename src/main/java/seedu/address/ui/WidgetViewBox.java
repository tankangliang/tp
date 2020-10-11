package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.widget.WidgetObject;

/**
 * An Ui component that displays the information of {@code WidgetObject}.
 */
public class WidgetViewBox extends UiPart<Region> {
    private static final String FXML = "WidgetViewBox.fxml";
    private final WidgetObject widgetObject;
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
    private Label divThree;
    @FXML
    private Label textThree;
    @FXML
    private Label divFour;
    @FXML
    private Label textFour;
    @FXML
    private Label divFive;
    @FXML
    private Label textFive;
    @FXML
    private Label divSix;
    @FXML
    private Label textSix;
    @FXML
    private Label footer;

    /**
     * Creates a {@code WidgetViewBox} with the given {@code WidgetObject}.
     */
    public WidgetViewBox(WidgetObject widgetObject) {
        super(FXML);
        this.widgetObject = widgetObject;
        header.setText(widgetObject.header().orElse(""));
        divOne.setText(widgetObject.divOne().orElse(""));
        divTwo.setText(widgetObject.divTwo().orElse(""));
        divThree.setText(widgetObject.divThree().orElse(""));
        divFour.setText(widgetObject.divFour().orElse(""));
        divFive.setText(widgetObject.divFive().orElse(""));
        divSix.setText(widgetObject.divSix().orElse(""));
        textOne.setText(widgetObject.textOne().orElse(""));
        textTwo.setText(widgetObject.textTwo().orElse(""));
        textThree.setText(widgetObject.textThree().orElse(""));
        textFour.setText(widgetObject.textFour().orElse(""));
        textFive.setText(widgetObject.textFive().orElse(""));
        textSix.setText(widgetObject.textSix().orElse(""));
        footer.setText(widgetObject.footer().orElse(""));
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
