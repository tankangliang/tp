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
        return Optional.ofNullable(divs[0]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div1.
     */
    public String divOne() {
        return Optional.ofNullable(divs[1]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text1.
     */
    public String textOne() {
        return Optional.ofNullable(divs[2]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div2.
     */
    public String divTwo() {
        return Optional.ofNullable(divs[3]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text2.
     */
    public String textTwo() {
        return Optional.ofNullable(divs[4]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div3.
     */
    public String divThree() {
        return Optional.ofNullable(divs[5]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text3.
     */
    public String textThree() {
        return Optional.ofNullable(divs[6]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div4.
     */
    public String divFour() {
        return Optional.ofNullable(divs[7]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text4.
     */
    public String textFour() {
        return Optional.ofNullable(divs[8]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div5.
     */
    public String divFive() {
        return Optional.ofNullable(divs[9]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text5.
     */
    public String textFive() {
        return Optional.ofNullable(divs[10]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div6.
     */
    public String divSix() {
        return Optional.ofNullable(divs[11]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text6.
     */
    public String textSix() {
        return Optional.ofNullable(divs[12]).orElse("");
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Footer.
     */
    public String footer() {
        return Optional.ofNullable(divs[13]).orElse("");
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
