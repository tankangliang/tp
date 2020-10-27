package seedu.address.model.widget;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;

/**
 * This is a separate model manager that separately manages the display contents of the proposed View Box left of
 * the filtered client list.
 */
public class WidgetModelManager implements WidgetModel {

    private static final Logger logger = LogsCenter.getLogger(WidgetModelManager.class);
    private Client client;

    private WidgetModelManager() {}

    static WidgetModelManager initWidget() {
        logger.info("----------------[ Creating WidgetModelManager ]");
        return new WidgetModelManager();
    }

    /**
     * Sets the client to be displayed to a given client.
     *
     * @param client
     */
    @Override
    public void setWidgetClient(Client client) {
        logger.info("Setting client of widget to: " + client.getName().fullName);
        this.client = client;
    }

    /**
     * Returns the widget content object.
     *
     * @return WidgetObject.
     */
    @Override
    public Client getWidgetClient() {
        logger.info("Retrieving client of widget: " + client.getName().fullName);
        return client;
    }

}
