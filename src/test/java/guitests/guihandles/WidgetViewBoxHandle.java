package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import seedu.address.model.widget.WidgetObject;

/**
 * Provides a handler to the widget view box.
 */
public class WidgetViewBoxHandle extends NodeHandle<Node> {
    private static final String HEADER_FIELD_ID = "#header";
    private static final String DIVONE_FIELD_ID = "#divOne";
    private static final String TEXTONE_FIELD_ID = "#textOne";
    private static final String DIVTWO_FIELD_ID = "#divTwo";
    private static final String TEXTTWO_FIELD_ID = "#textTwo";
    private static final String TEXTTHREE_FIELD_ID = "#textThree";
    private static final String DIVTHREE_FIELD_ID = "#divThree";
    private static final String TEXTFOUR_FIELD_ID = "#textFour";
    private static final String FOOTER_FIELD_ID = "#footer";
    private final Label headerLabel;
    private final Label divOneLabel;
    private final Label textOneLabel;
    private final Label divTwoLabel;
    private final Label textTwoLabel;
    private final Label textThreeLabel;
    private final Label divThreeLabel;
    private final TextArea textFourTextArea;
    private final Label footerLabel;


    //TODO: Change label of TextArea according to changes in widgetviewbox

    /**
     * Constructor for handler.
     *
     * @param widgetNode
     */
    public WidgetViewBoxHandle(Node widgetNode) {
        super(widgetNode);

        headerLabel = getChildNode(HEADER_FIELD_ID);
        divOneLabel = getChildNode(DIVONE_FIELD_ID);
        textOneLabel = getChildNode(TEXTONE_FIELD_ID);
        divTwoLabel = getChildNode(DIVTWO_FIELD_ID);
        textTwoLabel = getChildNode(TEXTTWO_FIELD_ID);
        textThreeLabel = getChildNode(TEXTTHREE_FIELD_ID);
        divThreeLabel = getChildNode(DIVTHREE_FIELD_ID);
        textFourTextArea = getChildNode(TEXTFOUR_FIELD_ID);
        footerLabel = getChildNode(FOOTER_FIELD_ID);
    }

    /**
     * Returns true if this handle contains a {@code otherObject}.
     */
    public boolean equals(WidgetObject otherObject) {
        return headerLabel.getText().equals(otherObject.getHeader())
                && divOneLabel.getText().equals(otherObject.getDivOne())
                && textOneLabel.getText().equals(otherObject.getTextOne())
                && divTwoLabel.getText().equals(otherObject.getDivTwo())
                && textTwoLabel.getText().equals(otherObject.getTextTwo())
                && textThreeLabel.getText().equals(otherObject.getTextThree())
                && divThreeLabel.getText().equals(otherObject.getDivThree())
                && textFourTextArea.getText().equals(otherObject.getTextFour())
                && footerLabel.getText().equals(otherObject.getFooter());
    }
}
