# SmartInventory

SmartInventory is a production-grade Inventory Management REST API that I built using Spring Boot 3, Java 17, and MySQL.

The project focuses on clean architecture, proper layering, and real-world inventory workflows such as stock tracking and transaction auditing, making it suitable for production use and future scalability.

---

## Key Highlights

- Clean layered architecture (Controller → Service → Repository)
- DTO-based API design (no entity leakage)
- Robust inventory stock handling (IN / OUT)
- Full audit trail for inventory transactions
- Global exception handling with meaningful HTTP status codes
- Input validation using Hibernate Validator
- Pagination & sorting support
- Multi-environment configuration (dev / prod)
- Unit & controller tests
- Docker-ready setup

---

## Tech Stack

| Layer | Technology |
|-----|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| ORM | Spring Data JPA / Hibernate |
| Database | MySQL |
| Validation | Hibernate Validator |
| API Docs | Swagger |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito, MockMvc |
| Containerization | Docker |

---

## Architecture Overview

```
Client
  ↓
Controller (REST APIs)
  ↓
Service (Business Logic)
  ↓
Repository (Database Access)
  ↓
MySQL Database
```

Design principles followed:
- Single Responsibility Principle
- Separation of Concerns
- Clean Code & Readability
- Production-safe error handling

---

## Project Structure

```
SmartInventory
├── src/main/java/com/smartinventory
│   ├── controller        # REST controllers
│   ├── service           # Business logic interfaces
│   │   └── impl           # Service implementations
│   ├── repository        # JPA repositories
│   ├── entity            # JPA entities
│   ├── dto               # Request / Response DTOs
│   │   ├── request
│   │   └── response
│   ├── exception         # Custom exceptions & handlers
│   ├── config            # Swagger, CORS, web config
│   ├── util              # Mappers, constants, helpers
│   └── SmartInventoryApplication.java
│
├── src/main/resources
│   ├── application.yml
│   ├── application-dev.yml
│   ├── application-prod.yml
│   ├── schema.sql
│   └── data.sql
│
├── src/test/java/com/smartinventory
│   ├── controller        # Controller tests
│   └── service           # Service tests
│
└── pom.xml
```

---

## ⚙️ Environment Configuration

### Profiles
- **dev** → Local development
- **prod** → Production / Docker

### application.yml
```yaml
spring:
  profiles:
    active: dev
```

---

## 🗄 Database Design

### Core Tables
- `categories`
- `products`
- `inventory`
- `inventory_transactions`

### Relationships
- Category → Products (One-to-Many)
- Product → Inventory (One-to-One)
- Product → InventoryTransactions (One-to-Many)

All tables include:
- `created_at`
- `updated_at`

---

## Running the Application (Local)

### Prerequisites
- Java 17
- Maven
- MySQL running locally

### Step 1: Create Database
```sql
CREATE DATABASE smart_inventory;
```

### Step 2: Update Credentials
Edit `application-dev.yml` if required:
```yaml
spring:
  datasource:
    username: root
    password: root
```

### Step 3: Run Application
```bash
mvn spring-boot:run
```

Server starts at:
```
http://localhost:8080
```

---

## Swagger API Documentation

Swagger UI is enabled and available at:

```
http://localhost:8080/swagger-ui.html
```

Features:
- Live API testing
- Request/response schemas
- Validation visibility

---

## API Endpoints

### Category APIs
- `POST   /api/categories`
- `GET    /api/categories`
- `GET    /api/categories/{id}`
- `PUT    /api/categories/{id}`
- `DELETE /api/categories/{id}`

### Product APIs
- `POST   /api/products`
- `GET    /api/products`
- `GET    /api/products/{id}`
- `PUT    /api/products/{id}`
- `DELETE /api/products/{id}`

### Inventory APIs
- `GET  /api/inventory/{productId}`
- `POST /api/inventory/in`
- `POST /api/inventory/out`

---

## Testing Strategy

Run all tests using:
```bash
mvn test
```

### Included Tests
- Service layer unit tests
- Controller layer tests using MockMvc
- Mockito-based dependency mocking

Testing ensures:
- Business logic correctness
- API contract safety

---

## Docker Support (Optional)

The project is Docker-ready and can be extended with:
- Dockerfile
- docker-compose (API + MySQL)

Ideal for:
- Production deployments
- CI/CD pipelines

---

## Error Handling

Centralized exception handling using `@RestControllerAdvice`:
- 400 – Bad Request
- 404 – Resource Not Found
- 409 – Insufficient Stock
- 500 – Internal Server Error

Validation errors return field-level messages.

---

## Future Enhancements

- JWT Authentication
- Role-Based Access Control (RBAC)
- Low stock alerts
- Inventory analytics & reports
- CI/CD pipeline integration
- Cloud deployment (AWS / GCP)

---


