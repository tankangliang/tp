package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // empty tag name -> invalid
        assertFalse(Tag.isValidTagName(""));

        // non-alphanumeric tag name -> invalid
        assertFalse(Tag.isValidTagName("hello'"));
        assertFalse(Tag.isValidTagName("hello it me"));

        // tag name longer than 45 characters -> invalid
        assertFalse(Tag.isValidTagName("pneumonoultramicroscopicsilicovolcanoconiosisk"));

        // tag name exactly 45 characters -> valid
        assertTrue(Tag.isValidTagName("pneumonoultramicroscopicsilicovolcanoconiosis"));
    }

}
