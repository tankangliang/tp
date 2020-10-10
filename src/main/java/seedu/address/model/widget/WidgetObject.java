package seedu.address.model.widget;

/**
 * A standardise way to render content on the widget view box.
 */
public class WidgetObject {
    private String header;
    private String div1;
    private String div2;
    private String div3;
    private String div4;
    private String div5;
    private String div6;
    private String footer;

    /**
     * Constructor for a widget object.
     */
    public WidgetObject() {
        header = null;
        div1 = null;
        div2 = null;
        div3 = null;
        div4 = null;
        div5 = null;
        div6 = null;
        footer = null;
    }

    /**
     * Sets the content of the widget object.
     *
     * @param args
     */
    public void set(String ...args) {
        header = args[0];
        div1 = args[1];
        div2 = args[2];
        div3 = args[3];
        div4 = args[4];
        div5 = args[5];
        div6 = args[6];
        footer = args[7];
    }

}
