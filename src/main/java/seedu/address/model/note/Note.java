package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Generic Note class for country and client notes.
 */
public class Note {
    public static final String MESSAGE_CONSTRAINTS = "message constraints for note";
    protected final String noteContents;
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a Note object with some content in it.
     *
     * @param content to be added
     */
    public Note(String content) {
        requireNonNull(content);
        noteContents = content;
    }

    /**
     * Adds a tag for this note into its list of tags.
     * @param tag The tag to be added.
     */
    public void addTag(Tag tag) {
        requireNonNull(tag);
        this.tags.add(tag);
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
    public Set<Tag> getTags() {
        return tags;
    }
}
