package com.example.notesbackend.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Objects;

/**
 * PUBLIC_INTERFACE
 * Represents a Note resource in the system.
 */
@Schema(description = "A Note with title and content.")
public class Note {

    /**
     * Unique identifier of the note.
     */
    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id;

    /**
     * Title of the note.
     */
    @Schema(description = "Title of the note", example = "Shopping list")
    private String title;

    /**
     * Content/body of the note.
     */
    @Schema(description = "Content/body of the note", example = "Milk, Eggs, Bread")
    private String content;

    /**
     * Creation timestamp (UTC).
     */
    @Schema(description = "Creation timestamp (UTC)", example = "2024-01-01T12:00:00Z")
    private Instant createdAt;

    /**
     * Last update timestamp (UTC).
     */
    @Schema(description = "Last update timestamp (UTC)", example = "2024-01-01T13:00:00Z")
    private Instant updatedAt;

    /**
     * No-args constructor.
     */
    public Note() {
    }

    /**
     * Full constructor.
     *
     * @param id        note id
     * @param title     note title
     * @param content   note content
     * @param createdAt created timestamp
     * @param updatedAt updated timestamp
     */
    public Note(final Long id, final String title, final String content,
                final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     * @return this note
     */
    public Note setId(final Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title
     * @return this note
     */
    public Note setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets the content.
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the content
     * @return this note
     */
    public Note setContent(final String content) {
        this.content = content;
        return this;
    }

    /**
     * Gets the creation time.
     *
     * @return createdAt
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time.
     *
     * @param createdAt creation time
     * @return this note
     */
    public Note setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Gets the update time.
     *
     * @return updatedAt
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update time.
     *
     * @param updatedAt update time
     * @return this note
     */
    public Note setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Note note = (Note) o;

        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
