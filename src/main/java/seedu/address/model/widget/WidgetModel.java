package seedu.address.model.widget;

import seedu.address.model.client.Client;

/**
 * This is the api for interacting with the proposed View Box widget in the TBM application. The view box widget model
 * is encapsulated within the TBM model itself.
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
     * @param client
     */
    void setWidgetClient(Client client);

    /**
     * Returns the widget content object.
     *
     * @return WidgetObject
     */
    Client getWidgetClient();
}
