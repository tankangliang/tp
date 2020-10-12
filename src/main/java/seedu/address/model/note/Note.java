package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import java.util.Objects;


/**
 * Generic Note class for country and client notes.
 */
public class Note {
    public static final String MESSAGE_CONSTRAINTS = "message contraints for note";
    protected final String noteContents;

    /**
     * Constructs a Note object with some content in it.
     *
     * @param content to be added
     */
    public Note(String content) {
        requireNonNull(content);
        noteContents = content;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof Note)) {
            return false;
        }

        // state check
        Note c = (Note) obj;

        return this.noteContents.equals(c.noteContents);
    }

    @Override
    public String toString() {
        return noteContents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteContents);
    }
}
