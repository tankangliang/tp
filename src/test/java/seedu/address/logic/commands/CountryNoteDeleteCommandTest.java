package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class CountryNoteDeleteCommandTest {
    @Test
    public void equals_sameIndex_returnsTrue() {
        Index index = Index.fromOneBased(1);
        CountryNoteDeleteCommand first = new CountryNoteDeleteCommand(index);
        CountryNoteDeleteCommand second = new CountryNoteDeleteCommand(index);
        assertEquals(first, second);
    }

    @Test
    public void equals_diffIndex_returnsFalse() {
        Index index = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);
        CountryNoteDeleteCommand first = new CountryNoteDeleteCommand(index);
        CountryNoteDeleteCommand second = new CountryNoteDeleteCommand(index2);
        assertNotEquals(first, second);
    }
}
