package com.example.notesbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Tag(name = "Hello Controller", description = "Basic endpoints for notesbackend")
public class HelloController {

    /**
     * PUBLIC_INTERFACE
     * Welcome endpoint.
     */
    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        return "Hello, Spring Boot! Welcome to notesbackend";
    }

    /**
     * PUBLIC_INTERFACE
     * Redirects to Swagger UI, preserving original scheme/host/port.
     */
    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI preserving original scheme/host/port")
    public RedirectView docs(HttpServletRequest request) {
        // Build absolute URL honoring forwarded headers without using the deprecated fromHttpRequest
        ServletServerHttpRequest serverRequest = new ServletServerHttpRequest(request);
        String scheme = serverRequest.getURI().getScheme();
        String host = serverRequest.getURI().getHost();
        int port = serverRequest.getURI().getPort();

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host);

        if (port != -1) {
            builder.port(port);
        }

        String target = builder
                .path("/swagger-ui.html")
                .build()
                .toUriString();

        RedirectView rv = new RedirectView(target);
        rv.setHttp10Compatible(false);
        return rv;
    }

    /**
     * PUBLIC_INTERFACE
     * Liveness probe endpoint.
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        return "OK";
    }

    /**
     * PUBLIC_INTERFACE
     * Basic app info.
     */
    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        return "Spring Boot Application: notesbackend";
    }
}