package seedu.address.model.note;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.model.client.Client;
import seedu.address.model.country.Country;
import seedu.address.model.tag.Tag;


/**
 * Manages the relationship between Tags and Notes.
 */

public class TagNoteMap {
    //    TODO: Add implementation
    public static final Set<Tag> TAG_LIST = new HashSet<>(); // probably redundant todo: remove later
    public static final Set<Note> NOTE_LIST = new HashSet<>();
    public static final Map<Tag, Set<Note>> TAG_TO_NOTES_MAP = new HashMap<>();
    public static final Map<Note, Set<Tag>> NOTE_TO_TAGS_MAP = new HashMap<>();

    /**
     * Initialises the TagNoteMap with a list of clients.
     *
     * @param clients The list of clients containing ClientNotes and associated tags.
     */
    public void initMapUsingClients(List<Client> clients) { // todo: make init work when passed in a list of countries
        for (Client client : clients) {
            Set<Note> clientNotes = client.getClientNotes();
            NOTE_LIST.addAll(clientNotes);
            for (Note clientNote : clientNotes) {
                Set<Tag> tags = clientNote.getTags();
                TAG_LIST.addAll(tags);
                updateTagsForNote(tags, clientNote);
            }
        }
        // todo: how to remove old entries to the map?
    }

    /**
     * Initialises the TagNoteMap with a list of countries.
     *
     * @param countries The list of clients containing ClientNotes and associated tags.
     */
    public void initMapUsingCountries(List<Country> countries) {
        // todo: make init work when passed in a list of countries
        for (Country myCountry : countries) {
            Set<Note> countryNotes = myCountry.getCountryNotes();
            NOTE_LIST.addAll(countryNotes);
            for (Note countryNote : countryNotes) {
                Set<Tag> tags = countryNote.getTags();
                TAG_LIST.addAll(tags);
                updateTagsForNote(tags, countryNote);
            }
        }
        // todo: how to remove old entries to the map? <== need to do this to "sync"
        //       with the current model right?
    }

    public Set<Tag> getTagsForNote(Note note) {
        return Collections.unmodifiableSet(NOTE_TO_TAGS_MAP.getOrDefault(note, new HashSet<>()));
    }

    public Set<Note> getNotesForTag(Tag tag) {
        return Collections.unmodifiableSet(TAG_TO_NOTES_MAP.getOrDefault(tag, new HashSet<>()));
    }

    /**
     * Links a new set of tags to a note.
     *
     * @param newTags The tags to associate with a particular note.
     * @param note    The note to associate the tag with.
     */
    public void updateTagsForNote(Set<Tag> newTags, Note note) {
        TAG_LIST.addAll(newTags);

        // update the notes set for each of the tags:
        for (Tag newTag : newTags) {
            if (TAG_TO_NOTES_MAP.containsKey(newTag)) { // if that tag exists
                TAG_TO_NOTES_MAP.get(newTag).add(note);
            } else { // new tag:
                Set<Note> notes = new HashSet<>();
                notes.add(note);
                TAG_TO_NOTES_MAP.put(newTag, notes);
            }
        }
        // update the tags set for the note:
        Set<Tag> currentTags = NOTE_TO_TAGS_MAP.getOrDefault(note, new HashSet<>());
        currentTags.addAll(newTags); // todo: check that this should work, right
    }

    /**
     * Links a new set of notes to a tag.
     *
     * @param newNotes The notes to associate with a particular tag.
     * @param tag      The tag to associate the notes with.
     */
    public void updateNotesForTag(Set<Note> newNotes, Tag tag) {
        NOTE_LIST.addAll(newNotes);
        // update the tags set for each of the notes:
        for (Note newNote : newNotes) {
            if (NOTE_TO_TAGS_MAP.containsKey(newNote)) {
                NOTE_TO_TAGS_MAP.get(newNote).add(tag);
            } else { // new note:
                Set<Tag> tags = new HashSet<>();
                tags.add(tag);
                NOTE_TO_TAGS_MAP.put(newNote, tags);
            }
        }
        //update the notes set for the tag:
        Set<Note> currentNotes = TAG_TO_NOTES_MAP.getOrDefault(tag, new HashSet<>());
        currentNotes.addAll(newNotes);
    }
}
