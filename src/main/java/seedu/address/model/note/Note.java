package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

public class Note {

    // TODO: Add implementation
    public static String MESSAGE_CONSTRAINTS = "message contraints for note";
    private final String noteContents;

    public Note(String content) {
        requireNonNull(content);
        noteContents = content;
    }
//    @Override
//    public String toString() {
//        return noteContents;
//    }
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if (!(other instanceof Note)) {
//            return false;
//        }
//    }
//    @Override
//    public int hashCode() {
//
//    }
}
