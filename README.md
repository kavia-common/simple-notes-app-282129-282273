# simple-notes-app-282129-282273

Backend: Spring Boot Notes API (notes_backend)

## Endpoints (base path /api/notes)
- POST / — Create a note
  - Request: { "title": "string (required, <=200)", "content": "string (<=5000)" }
  - Response: 201 Created, Location: /api/notes/{id}, body contains created Note
- GET / — List all notes
  - Response: 200 OK, body: Note[]
- GET /{id} — Get note by id
  - Response: 200 OK with Note or 404 Not Found
- PUT /{id} — Update note by id
  - Request: { "title": "string (required, <=200)", "content": "string (<=5000)" }
  - Response: 200 OK with updated Note, 404 Not Found if missing
- DELETE /{id} — Delete note by id
  - Response: 204 No Content or 404 Not Found

Note model:
```
{
  "id": number,
  "title": string,
  "content": string,
  "createdAt": string (ISO-8601),
  "updatedAt": string (ISO-8601)
}
```

## Validation
- 400 Bad Request returned for invalid payloads with field error map.

## Swagger/OpenAPI
- UI: /swagger-ui.html
- Docs JSON: /api-docs

## Health/Info
- GET /health
- GET /api/info

## CORS
- Enabled for localhost:3000 and 5173 on /api/** routes.

## Run
The application is configured to use the existing preview port. Start the Spring Boot app normally using Gradle or your IDE. No additional environment variables are required.

If a database is available in future, swap out the in-memory repository with a JPA repository; by default this app uses in-memory storage.