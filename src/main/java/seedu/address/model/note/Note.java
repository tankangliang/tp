package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

/**
 * Generic Note class for country and client notes.
 */
public class Note {

    // TODO: Add implementation
    public static final String MESSAGE_CONSTRAINTS = "message contraints for note";
    protected final String noteContents;

    /**
     * To be added.
     *
     * @param content to be added
     */
    public Note(String content) {
        requireNonNull(content);
        noteContents = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Note) {
            Note other = (Note) obj;
            return noteContents.equals(other.noteContents);
        }
        return false;
    }

    //  @Override
    //  public String toString() {
    //      return noteContents;
    //  }

    //  @Override
    //  public int hashCode() {
    //  }
}
