# Customer API

Customer registration REST API showcasing **advanced bean validation and centralized exception handling** in Spring Boot.

## Features

- Full CRUD for customers (in-memory store)
- **Custom constraint validators**:
  - `@StrongPassword` — 8+ chars, upper, lower, digit, special character
  - `@PhoneNumber` — E.164-style phone format
- **Nested validation** — `Address` object validated via `@Valid`
- **Standard constraints** — `@Email`, `@Size`, `@Pattern` (ZIP code), `@NotBlank`, `@NotNull`
- **Rich error responses** — per-field error map, request path, timestamp
- **Proper status codes** — `400` validation/malformed JSON, `404` not found, `409` duplicate email
- Password never serialized (`@JsonIgnore`)

## Tech Stack

- Java 17
- Spring Boot 4.0.9 (Web MVC, Validation)
- Maven (wrapper included)

## Run

```bash
./mvnw spring-boot:run
```

## Endpoints

| Method | Path                     | Description        |
|--------|--------------------------|--------------------|
| GET    | `/api/v1/customers`      | List customers     |
| GET    | `/api/v1/customers/{id}` | Get a customer     |
| POST   | `/api/v1/customers`      | Register customer  |
| PUT    | `/api/v1/customers/{id}` | Update customer    |
| DELETE | `/api/v1/customers/{id}` | Delete customer    |

## Example

```bash
curl -X POST localhost:8080/api/v1/customers \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Atif Munawar",
    "email": "atif@example.com",
    "phone": "+14155550123",
    "password": "S3cure!Pass",
    "address": {
      "street": "1 Main St",
      "city": "Springfield",
      "zipCode": "12345",
      "country": "USA"
    }
  }'
```

## Error response shape

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "password": "must be 8+ chars with upper, lower, digit and special character",
    "address.zipCode": "must be a valid ZIP code"
  },
  "path": "/api/v1/customers",
  "timestamp": "2026-11-15T01:30:00Z"
}
```

## Project Structure

```
src/main/java/io/github/atifmunawar34/customer/
├── customer/    # model, service, controller, DTOs, exceptions
├── common/      # ApiError, GlobalExceptionHandler
└── validation/  # custom constraints (@StrongPassword, @PhoneNumber)
```
