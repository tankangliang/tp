package seedu.address.model.note;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;


/**
 * Manages the relationship between Tags and Notes.
 */
public class TagNoteMap {
    //    TODO: Add implementation
    public final Set<Tag> tagSet = new HashSet<>(); // probably redundant todo: remove later
    public final Set<Note> noteSet = new HashSet<>();
    public final Map<Tag, Set<Note>> tagToNotesMap = new HashMap<>();
    public final Map<Note, Set<Tag>> noteToTagsMap = new HashMap<>();


    /**
     * Initialises the TagNoteMap from a list of clients.
     *
     * @param clients The list of clients, each containing their notes and associated tags.
     */
    public void initTagNoteMapFromClients(List<Client> clients) {
        requireAllNonNull(clients);
        for (Client client : clients) {
            Set<Note> clientNotes = client.getClientNotes();
            noteSet.addAll(clientNotes);
            for (Note clientNote : clientNotes) {
                Set<Tag> tags = clientNote.getTags();
                tagSet.addAll(tags);
                updateTagsForNote(tags, clientNote);
            }
        }
        // todo: how to remove old entries to the map?
    }

    /**
     * Initialises the TagNoteMap from a list of countries.
     *
     * @param countryNotes The set of countries, each containing their notes and associated tags.
     */
    public void initTagNoteMapFromCountryNotes(Set<Note> countryNotes) {
        // todo: make init work when passed in a list of countryNotes
        requireAllNonNull(countryNotes);
        noteSet.addAll(countryNotes);
        for (Note countryNote : countryNotes) {
            Set<Tag> tags = countryNote.getTags();
            tagSet.addAll(tags);
            updateTagsForNote(tags, countryNote);
        }
    }

    // todo: how to remove old entries to the map? <== need to do this to "sync"
    //       with the current model right?
    public Set<Tag> getTagsForNote(Note note) {
        return Collections.unmodifiableSet(noteToTagsMap.getOrDefault(note, new HashSet<>()));
    }

    public Set<Note> getNotesForTag(Tag tag) {
        return Collections.unmodifiableSet(tagToNotesMap.getOrDefault(tag, new HashSet<>()));
    }

    /**
     * Links a new set of tags to a note.
     * Method is public for ModelManager to use.
     * //todo doublecheck if this is unsafe.
     *
     * @param newTags The tags to associate with a particular note.
     * @param note    The note to associate the tag with.
     */
    public void updateTagsForNote(Set<Tag> newTags, Note note) {
        requireAllNonNull(newTags, note);
        tagSet.addAll(newTags);
        // update the notes set for each of the tags:
        for (Tag newTag : newTags) {
            if (tagToNotesMap.containsKey(newTag)) { // if that tag exists
                tagToNotesMap.get(newTag).add(note);
            } else { // new tag:
                Set<Note> notes = new HashSet<>();
                notes.add(note);
                tagToNotesMap.put(newTag, notes);
            }
        }
        // update the tags set for the note:
        Set<Tag> currentTags = noteToTagsMap.getOrDefault(note, new HashSet<>());
        currentTags.addAll(newTags);
        noteToTagsMap.put(note, currentTags);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object:
        if (obj == this) {
            return true;
        }
        // instance of handles nulls
        if (!(obj instanceof TagNoteMap)) {
            return false;
        }
        // state check:
        TagNoteMap other = (TagNoteMap) obj;
        return this.tagSet.equals(other.tagSet)
                && this.noteSet.equals(other.noteSet)
                && this.tagToNotesMap.equals(other.tagToNotesMap)
                && this.noteToTagsMap.equals(other.noteToTagsMap);
    }
}
