# TaskFlow API

A RESTful task management API built with Spring Boot. Tasks are stored in-memory (`ConcurrentHashMap`) — no database required.

## Features

- Full CRUD for tasks
- Task status (`TODO`, `IN_PROGRESS`, `DONE`) and priority (`LOW`, `MEDIUM`, `HIGH`)
- Filter tasks by status: `GET /api/v1/tasks?status=IN_PROGRESS`
- Request validation (Jakarta Bean Validation)
- Consistent error responses via `@RestControllerAdvice`

## Tech Stack

- Java 17
- Spring Boot 4.0.9 (Web, Validation)
- Maven (wrapper included — no install needed)

## Run

```bash
./mvnw spring-boot:run
```

API starts at `http://localhost:8080`.

## Endpoints

| Method | Path                        | Description              |
|--------|-----------------------------|--------------------------|
| GET    | `/api/v1/tasks`             | List tasks (`?status=`)  |
| GET    | `/api/v1/tasks/{id}`        | Get a task               |
| POST   | `/api/v1/tasks`             | Create a task            |
| PUT    | `/api/v1/tasks/{id}`        | Update a task            |
| DELETE | `/api/v1/tasks/{id}`        | Delete a task            |

## Example

```bash
# Create
curl -X POST localhost:8080/api/v1/tasks \
  -H 'Content-Type: application/json' \
  -d '{"title":"Write README","description":"Document the API","priority":"HIGH"}'

# List in-progress tasks
curl 'localhost:8080/api/v1/tasks?status=IN_PROGRESS'
```

## Project Structure

```
src/main/java/io/github/atifmunawar34/taskflow/
├── task/        # domain: model, enums, service, controller, DTOs
└── common/      # shared: error model, global exception handler
```
