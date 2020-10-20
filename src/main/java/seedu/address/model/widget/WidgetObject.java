package seedu.address.model.widget;

import java.util.Arrays;

/**
 * A standardised way to render content on the widget view box.
 * Truncates anything that does not fit into the defined 8 divisions/sections for the displaying in the view box.
 * Defined format for fields to be displayed is:
 * <ol>
 *     <li>Header</li>
 *     <li>Div 1</li>
 *     <li>Text 1</li>
 *     <li>Div 2</li>
 *     <li>Text 2</li>
 *     <li>Text 3</li>
 *     <li>Div 3</li>
 *     <li>Text 4</li>
 *     <li>Footer</li>
 * </ol>
 */
public class WidgetObject {

    public static final int NUMBER_OF_FIELDS = 9;
    private static final int HEADER_POS = 0;
    private static final int DIVONE_POS = 1;
    private static final int TEXTONE_POS = 2;
    private static final int DIVTWO_POS = 3;
    private static final int TEXTTWO_POS = 4;
    private static final int TEXTTHREE_POS = 5;
    private static final int DIVTHREE_POS = 6;
    private static final int TEXTFOUR_POS = 7;
    private static final int FOOTER_POS = 8;


    /**
     * Array is a collection of the fields to be mapped to the widget Ui component.
     *
     * [header, div1, text1, div2, text2, text3, div3, text4, footer]
     */
    private final String[] divs;

    /**
     * Constructor for a widget object.
     */
    public WidgetObject() {
        divs = new String[NUMBER_OF_FIELDS];
    }

    /**
     * Sets the content of the widget object. Any fields not set will be an empty string.
     * Fields set are in the following order:
     * <ol>
     *     <li>Header</li>
     *     <li>Div 1</li>
     *     <li>Text 1</li>
     *     <li>Div 2</li>
     *     <li>Text 2</li>
     *     <li>Text 3</li>
     *     <li>Div 3</li>
     *     <li>Text 4</li>
     *     <li>Footer</li>
     * </ol>
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
    public String getHeader() {
        return divs[HEADER_POS] == null ? "" : divs[HEADER_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div1.
     */
    public String getDivOne() {
        return divs[DIVONE_POS] == null ? "" : divs[DIVONE_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text1.
     */
    public String getTextOne() {
        return divs[TEXTONE_POS] == null ? "" : divs[TEXTONE_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div2.
     */
    public String getDivTwo() {
        return divs[DIVTWO_POS] == null ? "" : divs[DIVTWO_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text2.
     */
    public String getTextTwo() {
        return divs[TEXTTWO_POS] == null ? "" : divs[TEXTTWO_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text3.
     */
    public String getTextThree() {
        return divs[TEXTTHREE_POS] == null ? "" : divs[TEXTTHREE_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Div3.
     */
    public String getDivThree() {
        return divs[DIVTHREE_POS] == null ? "" : divs[DIVTHREE_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Text4.
     */
    public String getTextFour() {
        return divs[TEXTFOUR_POS] == null ? "" : divs[TEXTFOUR_POS];
    }

    /**
     * Getter for 'attributes'.
     *
     * @return Footer.
     */
    public String getFooter() {
        return divs[FOOTER_POS] == null ? "" : divs[FOOTER_POS];
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
