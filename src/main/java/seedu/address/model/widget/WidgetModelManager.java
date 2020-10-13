package seedu.address.model.widget;

import java.util.Arrays;

import seedu.address.commons.core.LogsCenter;
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
        if (content instanceof Client) {
            widgetObject = clientParser((Client) content);
        } else {
            widgetObject = handle(content);
        }

    }

    /**
     * Returns the widget content object.
     *
     * @return WidgetObject.
     */
    @Override
    public WidgetObject getWidgetContent() {
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
    private WidgetObject clientParser(Client client) {
        // TODO: Figure out an optimal display for all of client's fields
        WidgetObject newObj = new WidgetObject();
        String name = client.getName().toString();
        String location = client.getAddress().toString() + ", "
                + client.getCountry().getCountryName();
        String timezone = client.getTimezone().toString();
        String email = client.getEmail().toString();
        String phone = client.getPhone().toString();
        String tags = client.getTags().toString();

        newObj.set(/*header*/ name, /*div1*/ location, /*text1*/ "", /*div2*/ "", /*text2*/ email,
                /*div3*/ phone, /*text3*/ "", /*div4*/ tags);

        return newObj;
    }

    /**
     * Fail safe method to change any unhandled object into a WidgetObject.
     *
     * @param content An object of any type, Country, Note,... etc that is not handled.
     * @return WidgetObject.
     */
    private WidgetObject handle(Object content) {
        // TODO: v1.3 || v1.4 deprecate this method.
        java.util.logging.Logger logger = LogsCenter.getLogger(getClass());
        // Logs the unknown object being passed to this method
        logger.info(String.valueOf(content.getClass()));
        WidgetObject wo = new WidgetObject();
        java.lang.reflect.Field[] fields = content.getClass().getDeclaredFields();
        java.util.stream.Stream.of(fields).forEach(f -> {
            try {
                f.setAccessible(true);
                String fieldName = f.getName();
                String fieldValue = f.get(content).toString();
                logger.info(fieldName + ": " + fieldValue);
                StringBuilder sb = new StringBuilder().append(fieldName).append(": ").append(fieldValue);
                wo.set(sb.toString());
            } catch (IllegalAccessException ex) {
                // This path will never be reached
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        });
        return wo;
    }

}
