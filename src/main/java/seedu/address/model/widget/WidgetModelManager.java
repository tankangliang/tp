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
     * Sets the content to be displayed in the view box to be of that content. Breaks
     * down the content into a standardised format in specified in WidgetOject.
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

        newObj.set(header, div1, div2, div3, div4);
        return newObj;
    }

}
