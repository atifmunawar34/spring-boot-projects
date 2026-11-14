# Expense Tracker API

A RESTful expense tracking API built with Spring Boot, Spring Data JPA, and H2 — real database persistence with JPA entities, repositories, and transactions.

## Features

- Full CRUD for expenses persisted to H2 (JPA/Hibernate)
- Expense categories (`FOOD`, `TRANSPORT`, `UTILITIES`, `ENTERTAINMENT`, `HEALTH`, `OTHER`)
- Filter by category: `GET /api/v1/expenses?category=FOOD`
- Filter by date range: `GET /api/v1/expenses?from=2026-01-01&to=2026-01-31`
- Request validation (Jakarta Bean Validation, `BigDecimal` amounts)
- Transactional service layer (`@Transactional`)
- H2 console at `/h2-console`
- Consistent error responses via `@RestControllerAdvice`

## Tech Stack

- Java 17
- Spring Boot 4.0.9 (Web MVC, Data JPA, Validation)
- H2 Database (in-memory)
- Maven (wrapper included — no install needed)

## Run

```bash
./mvnw spring-boot:run
```

API starts at `http://localhost:8080`. H2 console: `http://localhost:8080/h2-console`
(JDBC URL `jdbc:h2:mem:expenses`, user `sa`, no password).

## Endpoints

| Method | Path                          | Description                          |
|--------|-------------------------------|--------------------------------------|
| GET    | `/api/v1/expenses`            | List (`?category=`, `?from=`, `?to=`)|
| GET    | `/api/v1/expenses/{id}`       | Get an expense                       |
| POST   | `/api/v1/expenses`            | Create an expense                    |
| PUT    | `/api/v1/expenses/{id}`       | Update an expense                    |
| DELETE | `/api/v1/expenses/{id}`       | Delete an expense                    |

## Example

```bash
# Create
curl -X POST localhost:8080/api/v1/expenses \
  -H 'Content-Type: application/json' \
  -d '{"title":"Groceries","amount":42.50,"category":"FOOD","expenseDate":"2026-11-15"}'

# Filter by category
curl 'localhost:8080/api/v1/expenses?category=FOOD'

# Filter by date range
curl 'localhost:8080/api/v1/expenses?from=2026-11-01&to=2026-11-30'
```

## Project Structure

```
src/main/java/io/github/atifmunawar34/expensetracker/
├── expense/     # entity, repository, service, controller, DTOs
└── common/      # shared: error model, global exception handler
```
