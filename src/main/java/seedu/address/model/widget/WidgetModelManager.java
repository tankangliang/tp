package seedu.address.model.widget;

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
        //Do nothing for now.
        //TODO: Set content (client) to the widget object either by typecasting or something else
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

    /**
     * Placeholder for the default content rendered on the widget.
     */
    private void defaultContent() {
        // TODO: Initialise the widget to a default content, should be some weather stuff
        WidgetObject wo = new WidgetObject();
        wo.set("Hello World! This is a header!", "Div 1", "Div2", "Div 3",
                "Div 4", "Div 5", "Div 6", "Copyright@ArthurBarbavsky");
        widgetObject = wo;
    }


}
