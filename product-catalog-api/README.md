# Product Catalog API

Product catalog REST API demonstrating **pagination, sorting, and dynamic filtering/search** with Spring Data JPA Specifications.

## Features

- Paginated search endpoint backed by `Pageable` + `JpaSpecificationExecutor`
- **Dynamic filters** — combine freely: `category`, `q` (name search), `minPrice`, `maxPrice`
- **Sorting** — `sort=<field>,<asc|desc>` with a whitelisted field set (rejects unknown fields with 400)
- **Page metadata** — `page`, `size`, `totalElements`, `totalPages`, `first`, `last`
- Page size clamped to a 100-item max
- Full CRUD + seed data (30 products via `CommandLineRunner`)
- Validation + structured error responses

## Tech Stack

- Java 17 · Spring Boot 4.0.9 (Web MVC, Data JPA, Validation) · H2 · Maven wrapper

## Run

```bash
./mvnw spring-boot:run
```

H2 console: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:catalog`, user `sa`).

## Endpoints

| Method | Path                    | Description                          |
|--------|-------------------------|--------------------------------------|
| GET    | `/api/v1/products`      | Search — paginated, sorted, filtered |
| GET    | `/api/v1/products/{id}` | Get a product                        |
| POST   | `/api/v1/products`      | Create a product                     |
| PUT    | `/api/v1/products/{id}` | Update a product                     |
| DELETE | `/api/v1/products/{id}` | Delete a product                     |

### Search parameters

| Param      | Example          | Notes                                     |
|------------|------------------|-------------------------------------------|
| `category` | `ELECTRONICS`    | `ELECTRONICS CLOTHING GROCERY BOOKS HOME` |
| `q`        | `keyboard`       | case-insensitive name contains            |
| `minPrice` | `20`             | inclusive                                 |
| `maxPrice` | `100`            | inclusive                                 |
| `page`     | `1`              | 0-based, default `0`                      |
| `size`     | `5`              | default `20`, max `100`                   |
| `sort`     | `price,desc`     | fields: `id name price stock category createdAt` |

## Examples

```bash
# Cheapest electronics, 5 per page
curl 'localhost:8080/api/v1/products?category=ELECTRONICS&sort=price,asc&size=5'

# Books between $30 and $50, page 2
curl 'localhost:8080/api/v1/products?category=BOOKS&minPrice=30&maxPrice=50&page=1&size=2'

# Name search
curl 'localhost:8080/api/v1/products?q=lamp'
```

## Response shape

```json
{
  "content": [ { "id": 1, "name": "Wireless Mouse", "price": 24.99, ... } ],
  "page": 0,
  "size": 5,
  "totalElements": 8,
  "totalPages": 2,
  "first": true,
  "last": false
}
```
