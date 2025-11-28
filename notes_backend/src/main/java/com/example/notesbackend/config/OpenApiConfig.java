package com.example.notesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * PUBLIC_INTERFACE
 * OpenAPI configuration and metadata.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Backend API",
                version = "0.1.0",
                description = "REST API for managing notes "
                        + "(create, read, update, delete).",
                contact = @Contact(name = "Notes Backend")
        ),
        tags = {
                @Tag(name = "Notes",
                        description = "CRUD operations for notes"),
                @Tag(name = "Hello Controller",
                        description = "Basic endpoints for notesbackend")
        }
)
public class OpenApiConfig {
    // No runtime beans needed; annotations provide metadata.
}
