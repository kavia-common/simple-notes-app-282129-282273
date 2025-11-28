package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * PUBLIC_INTERFACE
 * Simple in-memory repository for Notes using a ConcurrentHashMap.
 * Falls back when no database is configured or for lightweight usage.
 */
@Repository
public class InMemoryNoteRepository {

    private final Map<Long, Note> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    /**
     * PUBLIC_INTERFACE
     * Saves a new note and returns the persisted entity with id and timestamps populated.
     */
    public Note create(String title, String content) {
        Long id = seq.incrementAndGet();
        Instant now = Instant.now();
        Note note = new Note(id, title, content, now, now);
        store.put(id, note);
        return note;
    }

    /**
     * PUBLIC_INTERFACE
     * Updates an existing note. Returns Optional.empty if not found.
     */
    public Optional<Note> update(Long id, String title, String content) {
        Note existing = store.get(id);
        if (existing == null) return Optional.empty();
        existing.setTitle(title);
        existing.setContent(content);
        existing.setUpdatedAt(Instant.now());
        store.put(id, existing);
        return Optional.of(existing);
    }

    /**
     * PUBLIC_INTERFACE
     * Returns all notes sorted by id ascending.
     */
    public List<Note> findAll() {
        List<Note> notes = new ArrayList<>(store.values());
        notes.sort(Comparator.comparing(Note::getId));
        return notes;
    }

    /**
     * PUBLIC_INTERFACE
     * Finds note by id.
     */
    public Optional<Note> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * PUBLIC_INTERFACE
     * Deletes note by id. Returns true if deleted, false if not present.
     */
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
