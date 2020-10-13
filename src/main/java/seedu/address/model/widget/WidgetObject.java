package seedu.address.model.widget;

import java.util.Arrays;
import java.util.Optional;

/**
 * A standardise way to render content on the widget view box.
 * Truncates anything that does not fit into the defined 8 divisions/sections for the displaying in the view box.
 * Defined format for fields to be displayed is:
 * <ol>
 *     <li>Header</li>
 *     <li>Div 1</li>
 *     <li>Text 1</li>
 *     <li>Div 2</li>
 *     <li>Text 2</li>
 *     <li>Div 3</li>
 *     <li>Text 3</li>
 *     <li>Div 4</li>
 *     <li>Text 4</li>
 *     <li>Div 5</li>
 *     <li>Text 5</li>
 *     <li>Div 6</li>
 *     <li>Text 6</li>
 *     <li>Footer</li>
 * </ol>
 */
public class WidgetObject {

    public static final int NUMBER_OF_FIELDS = 14;

    /**
     * Array is a collection of the fields to be mapped to the widget Ui component.
     *
     * [header, div1, text1, div2, text2, div3, text3, div4, text4, div5, text5, div6, text6, footer]
     */
    private final String[] divs;

    /**
     * Constructor for a widget object.
     */
    public WidgetObject() {
        divs = new String[14];
    }

    /**
     * Sets the content of the widget object. Any fields not set will be an empty string.
     *
     * @param args All the fields to be set.
     */
    public void set(String... args) {
        // Sets all fields from start to end order.
        for (int i = 0; i < Math.min(args.length, NUMBER_OF_FIELDS); i++) {
            divs[i] = args[i];
        }
    }

    /**
     * Sets the first null field to the given String value. Any fields not set will be an empty string.
     *
     * @param arg The String value to be set to the first null field.
     */
    public void set(String arg) {
        System.out.println(arg);
        for (int i = 0; i < divs.length; i++) {
            if (divs[i] == null) {
                divs[i] = arg;
                return;
            }
        }
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Header.
     */
    public String header() {
        return divs[0] == null ? "" : divs[0];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div1.
     */
    public String divOne() {
        return divs[1] == null ? "" : divs[1];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text1.
     */
    public String textOne() {
        return divs[2] == null ? "" : divs[2];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div2.
     */
    public String divTwo() {
        return divs[3] == null ? "" : divs[3];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text2.
     */
    public String textTwo() {
        return divs[4] == null ? "" : divs[4];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div3.
     */
    public String divThree() {
        return divs[5] == null ? "" : divs[5];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text3.
     */
    public String textThree() {
        return divs[6] == null ? "" : divs[6];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div4.
     */
    public String divFour() {
        return divs[7] == null ? "" : divs[7];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text4.
     */
    public String textFour() {
        return divs[8] == null ? "" : divs[8];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div5.
     */
    public String divFive() {
        return divs[9] == null ? "" : divs[9];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text5.
     */
    public String textFive() {
        return divs[10] == null ? "" : divs[10];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div6.
     */
    public String divSix() {
        return divs[11] == null ? "" : divs[11];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text6.
     */
    public String textSix() {
        return divs[12] == null ? "" : divs[12];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Footer.
     */
    public String footer() {
        return divs[13] == null ? "" : divs[13];
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WidgetObject)) {
            return false;
        }

        // state check
        WidgetObject other1 = (WidgetObject) other;
        return Arrays.equals(this.divs, other1.divs);
    }

}
