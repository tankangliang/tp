package seedu.address.model.widget;

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

    // [header, div1, text1, div2, text2, div3, text3, div4, text4, div5, text5, div6, text6, footer]
    private final String[] divs;

    /**
     * Constructor for a widget object.
     */
    public WidgetObject() {
        divs = new String[14];
    }

    /**
     * Sets the content of the widget object.
     *
     * @param args All the fields to be set.
     */
    public void set(String ...args) {
        for (int i = 0; i < Math.min(args.length, 14); i++) {
            divs[i] = args[i];
        }
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Header.
     */
    public Optional<String> header() {
        return Optional.ofNullable(divs[0]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div1.
     */
    public Optional<String> divOne() {
        return Optional.ofNullable(divs[1]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text1.
     */
    public Optional<String> textOne() {
        return Optional.ofNullable(divs[2]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div2.
     */
    public Optional<String> divTwo() {
        return Optional.ofNullable(divs[3]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text2.
     */
    public Optional<String> textTwo() {
        return Optional.ofNullable(divs[4]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div3.
     */
    public Optional<String> divThree() {
        return Optional.ofNullable(divs[5]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text3.
     */
    public Optional<String> textThree() {
        return Optional.ofNullable(divs[6]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div4.
     */
    public Optional<String> divFour() {
        return Optional.ofNullable(divs[7]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text4.
     */
    public Optional<String> textFour() {
        return Optional.ofNullable(divs[8]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div5.
     */
    public Optional<String> divFive() {
        return Optional.ofNullable(divs[9]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text5.
     */
    public Optional<String> textFive() {
        return Optional.ofNullable(divs[10]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div6.
     */
    public Optional<String> divSix() {
        return Optional.ofNullable(divs[11]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text6.
     */
    public Optional<String> textSix() {
        return Optional.ofNullable(divs[12]);
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Footer.
     */
    public Optional<String> footer() {
        return Optional.ofNullable(divs[13]);
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
        int i = 0;
        for (String s: divs) {
            if (s == null && other1.divs[i] != null) {
                return false;
            } else {
                if (!s.equals(other1.divs[i])) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

}
