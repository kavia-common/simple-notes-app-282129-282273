package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.InMemoryNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * PUBLIC_INTERFACE
 * Service for managing notes.
 */
@Service
public class NoteService {

    /**
     * Repository used for persistence operations.
     */
    private final InMemoryNoteRepository repository;

    /**
     * Creates a new NoteService.
     *
     * @param repository the repository to use
     */
    public NoteService(final InMemoryNoteRepository repository) {
        this.repository = repository;
    }

    /**
     * PUBLIC_INTERFACE
     * Creates a new note from the given request.
     *
     * @param req the create request payload
     * @return the created Note
     */
    public Note create(final NoteCreateRequest req) {
        return repository.create(req.getTitle(), req.getContent());
    }

    /**
     * PUBLIC_INTERFACE
     * Updates an existing note by id using the given request.
     *
     * @param id  the note identifier
     * @param req the update request payload
     * @return Optional containing updated Note if found; otherwise empty
     */
    public Optional<Note> update(final Long id, final NoteUpdateRequest req) {
        return repository.update(id, req.getTitle(), req.getContent());
    }

    /**
     * PUBLIC_INTERFACE
     * Returns all notes.
     *
     * @return list of notes
     */
    public List<Note> list() {
        return repository.findAll();
    }

    /**
     * PUBLIC_INTERFACE
     * Returns a note by id if present.
     *
     * @param id the note identifier
     * @return Optional containing the note if found; otherwise empty
     */
    public Optional<Note> get(final Long id) {
        return repository.findById(id);
    }

    /**
     * PUBLIC_INTERFACE
     * Deletes a note by id.
     *
     * @param id the note identifier
     * @return true if the note existed and was deleted; false otherwise
     */
    public boolean delete(final Long id) {
        return repository.deleteById(id);
    }
}
