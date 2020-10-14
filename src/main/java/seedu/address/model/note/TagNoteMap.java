package seedu.address.model.note;


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
    public final Set<Tag> tagList = new HashSet<>(); // probably redundant todo: remove later
    public final Set<Note> noteSet = new HashSet<>();
    public final Map<Tag, Set<Note>> tagToNotesMap = new HashMap<>();
    public final Map<Note, Set<Tag>> noteToTagsMap = new HashMap<>();

    /**
     * Initialises the TagNoteMap with a list of clients.
     *
     * @param clients The list of clients containing ClientNotes and associated tags.
     */
    public void initMapFromClients(List<Client> clients) {
        for (Client client : clients) {
            Set<Note> clientNotes = client.getClientNotes();
            noteSet.addAll(clientNotes);
            for (Note clientNote : clientNotes) {
                Set<Tag> tags = clientNote.getTags();
                tagList.addAll(tags);
                updateTagsForNote(tags, clientNote);
            }
        }
        // todo: how to remove old entries to the map?
    }

    /**
     * Initialises the TagNoteMap with a list of countries.
     *
     * @param countryNotes The set of countries containing ClientNotes and associated tags.
     */
    public void initMapFromCountries(Set<Note> countryNotes) {
        // todo: make init work when passed in a list of countries
        noteSet.addAll(countryNotes);
        for (Note countryNote : countryNotes) {
            Set<Tag> tags = countryNote.getTags();
            tagList.addAll(tags);
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
     *
     * @param newTags The tags to associate with a particular note.
     * @param note    The note to associate the tag with.
     */
    public void updateTagsForNote(Set<Tag> newTags, Note note) {
        tagList.addAll(newTags);
        tagList.addAll(newTags);

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
        currentTags.addAll(newTags); // todo: check that this should work, right
    }

    /**
     * Links a new set of notes to a tag.
     *
     * @param newNotes The notes to associate with a particular tag.
     * @param tag      The tag to associate the notes with.
     */
    public void updateNotesForTag(Set<Note> newNotes, Tag tag) {
        noteSet.addAll(newNotes);
        // update the tags set for each of the notes:
        for (Note newNote : newNotes) {
            if (noteToTagsMap.containsKey(newNote)) {
                noteToTagsMap.get(newNote).add(tag);
            } else { // new note:
                Set<Tag> tags = new HashSet<>();
                tags.add(tag);
                noteToTagsMap.put(newNote, tags);
            }
        }
        //update the notes set for the tag:
        Set<Note> currentNotes = tagToNotesMap.getOrDefault(tag, new HashSet<>());
        currentNotes.addAll(newNotes);
    }
}
