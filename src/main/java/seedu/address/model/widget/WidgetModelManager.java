package seedu.address.model.widget;

import java.util.Arrays;

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
            widgetObject = clientParser(content);
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
    @SuppressWarnings("unchecked")
    private WidgetObject clientParser(Object content) {
        assert content instanceof Client;

        // TODO: Extract information out from client object
        WidgetObject newObj = new WidgetObject();
        Client curr = (Client) content;
        String name = curr.getName().toString();
        String location = curr.getAddress().toString() + ", "
                + curr.getCountry().getCountryName() + ", "
                + curr.getTimezone().toString();
        String email = curr.getEmail().toString();
        String div3 = curr.getPhone().toString();
        String div4 = "Notes:\n- Angel Investor\n- China Scholar";

        newObj.set(/*header*/ name, /*div1*/ location, /*text1*/ "", /*div2*/ "", /*text2*/ email,
                /*div3*/ div3, /*text3*/ "", /*div4*/ div4);
        return newObj;
    }

    /**
     * Temporary method to handle objects of any class.
     */
    private WidgetObject handle(Object content) {
        WidgetObject wo = new WidgetObject();
        java.lang.reflect.Field[] fields = content.getClass().getDeclaredFields();
        java.util.stream.Stream.of(fields).forEach(f -> {
            try {
                f.setAccessible(true);
                StringBuilder sb = new StringBuilder().append(f.getName()).append(": ").append(f.get(content));
                wo.set(sb.toString());
            } catch (IllegalAccessException ex) {
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        });
        return wo;
    }

}
