package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * PUBLIC_INTERFACE
 * Request payload for creating a new Note.
 */
@Schema(description = "Payload to create a new note")
public class NoteCreateRequest {

    @NotBlank(message = "title is required")
    @Size(max = 200, message = "title must be <= 200 characters")
    @Schema(description = "Title of the note", example = "Meeting notes", maxLength = 200, requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Size(max = 5000, message = "content must be <= 5000 characters")
    @Schema(description = "Content/body of the note", example = "Discuss Q1 roadmap", maxLength = 5000)
    private String content;

    public NoteCreateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public NoteCreateRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteCreateRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
