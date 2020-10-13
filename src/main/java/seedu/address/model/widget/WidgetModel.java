package seedu.address.model.widget;

/**
 * This is the api for interacting with the proposed View Box widget in the TBM application. The view box widget model
 * is encapsulated within the TBM model itself.
 *
 * v1.2 - display contents are limited to objects of Client type.
 * v1.3 - ???
 */
public interface WidgetModel {

    /**
     * Static factory.
     *
     * @return WidgetModel implemented by WidgetModelMoanager.
     */
    static WidgetModel initWidget() {
        return WidgetModelManager.initWidget();
    };

    /**
     * Sets the content to be displayed in the view box to be of that content.
     *
     * @param content
     */
    void setContent(Object content);

    /**
     * Returns the widget content object.
     *
     * @return WidgetObject
     */
    WidgetObject getWidgetContent();
}
