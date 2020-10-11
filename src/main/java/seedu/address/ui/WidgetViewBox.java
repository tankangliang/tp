package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.widget.WidgetObject;

/**
 *
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
    }
}
