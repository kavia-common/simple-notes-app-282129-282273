package com.example.notesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * Application bootstrap for Notes Backend.
 */
@SpringBootApplication
public class NotesbackendApplication {

    /**
     * Main entrypoint.
     *
     * @param args application arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(NotesbackendApplication.class, args);
    }
}
