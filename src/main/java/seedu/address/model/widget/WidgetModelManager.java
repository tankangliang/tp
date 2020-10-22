package seedu.address.model.widget;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.note.Note;

/**
 * This is a separate model manager that separately manages the display contents of the proposed View Box left of
 * the filtered client list.
 */
public class WidgetModelManager implements WidgetModel {
    private static final Logger logger = LogsCenter.getLogger(WidgetModelManager.class);
    private WidgetObject widgetObject;

    private WidgetModelManager() {
        widgetObject = new WidgetObject();
    }

    static WidgetModelManager initWidget() {
        logger.info("----------------[ Creating WidgetModelManager ]");
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
        } else if (content instanceof List) {
            // country notes list
            widgetObject = countryNotesParser((List<CountryNote>) content);
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
     * Maps content of the given object to different fields of the widget object.
     */
    private WidgetObject clientParser(Client client) {
        logger.info("Setting content of widget to client: " + client.getName().toString());
        WidgetObject newObj = new WidgetObject();
        String name = client.getName().toString();
        String country = client.getCountry().getCountryName();
        String timezone = client.getTimezone().toString();
        String email = client.getEmail().toString();
        String phone = client.getPhone().toString();
        String notes = client.getClientNotes().toString(); //TODO: Temporary display for the client notes
        String tags = Stream.of(client.getTags())
                .map(Object::toString)
                .collect(Collectors.joining())
                .replace("[", "")
                .replace("]", "");

        newObj.set(/*header*/ name, /*div1*/ country, /*text1*/ timezone, /*div2*/ "", /*text2*/ email,
                /*text3*/ phone, /*div3*/ "", /*text4 SCROLLABLE VIEW*/ notes, /*footer*/ tags);

        return newObj;
    }

    /**
     * Maps content of the country note list to the widget object.
     */
    private WidgetObject countryNotesParser(List<CountryNote> countryNoteList) {
        logger.info("Setting content of widget to country note list: ");
        WidgetObject newObj = new WidgetObject();
        StringBuilder combinedNotes = new StringBuilder();
        Country country = countryNoteList.get(0).getCountry();

        for (CountryNote countryNote : countryNoteList) {
            combinedNotes.append(countryNote.getNoteContents()).append("\n");
        }

        newObj.set(/*header*/ country.getCountryName() + " Notes", /*div1*/ combinedNotes.toString(), /*text1*/ "", /*div2*/ "", /*text2*/ "",
            /*text3*/ "", /*div3*/ "", /*text4 SCROLLABLE VIEW*/ "", /*footer*/ "");

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
        // Logs the unknown object being passed to this method
        logger.info("=============================[ Client object was not passed as content ]"
                + "=============================");
        logger.info("Setting content of widget to: " + content.getClass());
        WidgetObject wo = new WidgetObject();
        java.lang.reflect.Field[] fields = content.getClass().getDeclaredFields();
        java.util.stream.Stream.of(fields).forEach(f -> {
            try {
                f.setAccessible(true);
                String fieldName = f.getName();
                String fieldValue = f.get(content).toString();
                logger.info("----- [ Warning! Object passed is not a client! ] -----" + fieldName + ": " + fieldValue);
                StringBuilder sb = new StringBuilder().append(fieldName).append(": ").append(fieldValue);
                wo.set(sb.toString());
            } catch (IllegalAccessException ex) {
                logger.severe("----- [ Fatal Error: Accessing private fields of unknown object ] -----");
                logger.severe(Arrays.toString(ex.getStackTrace()));
            } catch (Exception e) {
                logger.severe("----- [ Fatal Error ] -----");
                logger.severe(Arrays.toString(e.getStackTrace()));
            }
        });
        return wo;
    }

}
