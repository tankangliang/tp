package seedu.address.model.tag;

public class TagFactory {
//    TODO: Add implementation
//    public static final Set<Tag> TAG_LIST = new HashSet<>();
//    public static final Map<Tag, Set<Note>> TAG_TO_NOTE_MAP = new HashMap<>();
//    public static final Map<Note, Set<Tag>> NOTE_TO_TAG_MAP = new HashMap<>();
//
//    public void initializeTagList() {
//        // TODO: get tags from data file
//    }
//
//    public Set<Tag> getTagsForNote(Note note) {
//        return Collections.unmodifiableSet(NOTE_TO_TAG_MAP.getOrDefault(note, new HashSet<>()));
//    }
//
//    public Set<Note> getNotesForTag(Tag tag) {
//        return Collections.unmodifiableSet(TAG_TO_NOTE_MAP.getOrDefault(tag, new HashSet<>()));
//    }
//
//    public void updateTagsForNote(Set<Tag> newTags, Note note) {
//        TAG_LIST.addAll(newTags);
//        for (Tag newTag : newTags) {
//            if (TAG_TO_NOTE_MAP.contains(newTag)) {
//                TAG_TO_NOTE_MAP.get(newTag).add(note);
//            } else {
//                TAG_TO_NOTE_MAP.put(newTag, new HashSet<>(note));
//            }
//        }
//
//        Set<Tag> removedTags = NOTE_TO_TAG_MAP.getOrDefault(note, new HashSet<>()).removeAll(newTags);
//        for (Tag removedTag : removedTags) {
//            Set<Note> noteSet = TAG_TO_NOTE_MAP.get(removedTag);
//            noteSet.remove(note);
//            if (noteSet.size() == 0) {
//                TAG_LIST.remove(removedTag);
//                TAG_TO_NOTE_MAP.remove();
//            }
//        }
//        NOTE_TO_TAG_MAP.put(note, newTags);
//    }
}
