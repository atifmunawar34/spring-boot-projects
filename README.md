# Spring Boot Projects

A growing collection of production-grade Spring Boot projects — REST APIs, security, microservices, testing, and DevOps practices. One project added daily.

## Projects

| Project | Description | Stack |
|---------|-------------|-------|
| [taskflow-api](taskflow-api/) | Task management REST API with validation, status/priority filtering, and structured error responses | Spring Boot 4, Web MVC, Validation |
| [expense-tracker-api](expense-tracker-api/) | Expense tracking API with JPA persistence, category & date-range filters, H2 console | Spring Boot 4, Data JPA, H2, Validation |
| [customer-api](customer-api/) | Customer registration API with custom validators (@StrongPassword, @PhoneNumber), nested validation, structured errors | Spring Boot 4, Web MVC, Validation |

Full 60-day plan: [ROADMAP.md](ROADMAP.md)

## Conventions

- Java 17, Spring Boot 4.x, Maven Wrapper (`./mvnw` — no local Maven needed)
- Versioned APIs under `/api/v1/`
- Package naming: `io.github.atifmunawar34.<project>`
- Each project is self-contained with its own README and run instructions

## Run any project

```bash
cd <project-name>
./mvnw spring-boot:run
```

APIs start at `http://localhost:8080`.

## Roadmap highlights

- **Days 1–10** — Core REST & data fundamentals
- **Days 11–18** — Databases (JPA, transactions, MongoDB, Redis)
- **Days 19–24** — Security (JWT, RBAC, OAuth2)
- **Days 25–30** — Async & messaging (WebClient, WebSocket, Kafka)
- **Days 31–36** — Testing (Testcontainers, WireMock, mutation testing)
- **Days 37–48** — Microservices (Eureka, Gateway, Resilience4j, sagas, capstone)
- **Days 49–56** — Production & DevOps (Docker, CI/CD, Kubernetes, observability)
- **Days 57–60** — Advanced (GraphQL, gRPC, Elasticsearch)
