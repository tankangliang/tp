package seedu.address.model.widget;

import seedu.address.model.client.Client;

/**
 * This is a separate model manager that separately manages the display contents of the proposed View Box left of
 * the filtered client list.
 */
public class WidgetModelManager implements WidgetModel {
    private WidgetObject widgetObject;

    private WidgetModelManager() {}

    static WidgetModelManager initWidget() {
        WidgetModelManager view = new WidgetModelManager();
        view.defaultContent();
        return new WidgetModelManager();
    }
    /**
     * <p>Sets the content to be displayed in the view box to be of that content. Break down the content into a
     * standardised format in specified in {@code WidgetOject}.</p>
     *
     * <p>In creating the widget object, the values for each of the widget object fields can be set to a String value.
     * In order to do so, the targeted object to be displayed, needs to have its contents/fields/members stringified.
     * After stringifying the attributes, call {@link WidgetObject#set(String...) set}, where args is an ordered set of
     * Strings. Hence, the first String will be set as the value for the header field of the widget object, the second
     * String will be set to the div1 field of the widget object so on and so forth.</p>
     *
     * @param content
     */
    @Override
    public void setContent(Object content) {
        if (content.getClass() == Client.class) {
            widgetObject = clientParser(content);
        } else {
            return;
        }
    }

    /**
     * Returns the widget content object.
     *
     * @return WidgetObject.
     */
    @Override
    public WidgetObject getWidgetConten() {
        return widgetObject;
    }

    /* ============================================================================================================== */

    /**
     * Stub for the default content rendered on the widget.
     */
    private void defaultContent() {
        // TODO: Initialise the widget to a default content - weather, locale, local time
        WidgetObject wo = new WidgetObject();
        wo.set("Hello World! This is a header!", "Div 1", "Div2", "Div 3",
                "Div 4", "Div 5", "Div 6", "Copyright@ArthurBarbavsky");
        widgetObject = wo;
    }

    /**
     * Maps content of the given object to different fields of the widget object.
     */
    @SuppressWarnings("unchecked")
    private WidgetObject clientParser(Object content) {
        assert content instanceof Client;

        // TODO: Extract information out from client object
        WidgetObject newObj = new WidgetObject();
        Client curr = (Client) content;
        String header = curr.getName().toString();
        String div1 = curr.getCountry().getCountryName() + ", " + curr.getAddress().toString();
        String div2 = curr.getEmail().toString();
        String div3 = curr.getPhone().toString();
        String div4 = "Notes:\n- Angel Investor\n- China Scholar";

        newObj.set(/*header*/ header, /*div1*/ div1, /*text1*/ "", /*div2*/ div2, /*text2*/ "",
                /*div3*/ div3, /*text3*/ "", /*div4*/ div4);
        return newObj;
    }

}
