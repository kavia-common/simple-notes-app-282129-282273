package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * PUBLIC_INTERFACE
 * Request payload for updating an existing Note.
 */
@Schema(description = "Payload to update an existing note")
public class NoteUpdateRequest {

    @NotBlank(message = "title is required")
    @Size(max = 200, message = "title must be <= 200 characters")
    @Schema(description = "Updated title of the note", example = "Updated meeting notes", maxLength = 200, requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Size(max = 5000, message = "content must be <= 5000 characters")
    @Schema(description = "Updated content/body of the note", example = "Updated: Discussed budgeting", maxLength = 5000)
    private String content;

    public NoteUpdateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public NoteUpdateRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteUpdateRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
