package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PUBLIC_INTERFACE
 * REST API controller providing CRUD operations for Notes.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    /**
     * PUBLIC_INTERFACE
     * Create a new note.
     *
     * Returns 201 Created with Location header and created note in body.
     */
    @PostMapping("/")
    @Operation(
            summary = "Create note",
            description = "Creates a new note with title and content.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid payload")
            }
    )
    public ResponseEntity<Note> create(@Valid @RequestBody NoteCreateRequest request) {
        Note created = service.create(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/notes/" + created.getId()));
        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }

    /**
     * PUBLIC_INTERFACE
     * List all notes.
     */
    @GetMapping("/")
    @Operation(summary = "List notes", description = "Returns all notes.")
    public ResponseEntity<List<Note>> list() {
        return ResponseEntity.ok(service.list());
    }

    /**
     * PUBLIC_INTERFACE
     * Get a single note by id.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get note", description = "Returns a single note by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    public ResponseEntity<Note> get(@PathVariable("id") Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * PUBLIC_INTERFACE
     * Update an existing note by id.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Updates an existing note by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid payload"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    public ResponseEntity<Note> update(@PathVariable("id") Long id,
                                       @Valid @RequestBody NoteUpdateRequest request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * PUBLIC_INTERFACE
     * Delete a note by id.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note", description = "Deletes a note by id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * PUBLIC_INTERFACE
     * Handle validation errors to return 400 with field messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Validation failed");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }
}
