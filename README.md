# HexaModulith: Spring Modulith + Hexagonal (Ports & Adapters) + Clean Architecture

A demonstration project showcasing how to combine:
- **Spring Modulith** - For modular monolith architecture
- **Hexagonal Architecture (Ports & Adapters)** - For decoupling domain from infrastructure
- **Clean Architecture** - For dependency inversion and separation of concerns

## Project Structure

```
hexamodulith/
├── app/                          # Main application module (bootstraps everything)
├── modules/
│   ├── inventory/                # Inventory module
│   │   ├── inventory-core/       # Core domain + application services + ports
│   │   └── inventory-adapters/   # Adapters (REST, persistence, messaging)
│   └── stock/                    # Stock module
│       ├── stock-core/           # Core domain + application services + ports
│       └── stock-adapters/       # Adapters (REST, persistence, messaging)
├── shared-kernel/                # Shared domain events and utilities
└── outbox-module/                # Outbox pattern implementation
```

## Key Architectural Concepts

### 1. Spring Modulith
Spring Modulith helps you structure your application into logical modules with clear boundaries. It provides:
- Module isolation and dependency checking
- Event-driven communication between modules
- Architecture validation tests

### 2. Hexagonal Architecture (Ports & Adapters)
Each module is structured with:
- **Core**: Domain model, application services, and ports (interfaces)
- **Adapters**: Implementations of ports (REST controllers, JPA repositories, event listeners)

```
Module Structure:
├── core/
│   ├── domain/          # Domain entities and business logic
│   ├── application/     # Use cases / application services
│   └── port/
│       ├── in/          # Input ports (use case interfaces)
│       └── out/         # Output ports (repository interfaces, etc.)
└── adapters/
    ├── rest/            # REST controllers (driving adapters)
    ├── persistence/     # JPA repositories (driven adapters)
    ├── messaging/       # Event listeners/publishers
    └── config/          # Module configuration
```

### 3. Clean Architecture
The project follows Clean Architecture principles:
- Dependencies point inward toward the domain
- Domain layer has no external dependencies
- Use cases orchestrate domain objects
- Adapters implement the ports defined by the core

## Modules Overview

### Inventory Module
Manages inventory items with the following features:
- Create inventory items
- Decrease stock
- Apply stock updates

### Stock Module
(Add your stock module features here)

### Shared Kernel
Contains shared components used across modules:
- Domain events
- Outbox pattern annotations

### Outbox Module
Implements the Transactional Outbox Pattern for reliable event delivery.

## Getting Started

### Prerequisites
- Java 22
- Maven 3.x

### Build the Project
```bash
./mvnw clean install
```

### Run the Application
```bash
./mvnw spring-boot:run -pl app
```

### Access Swagger UI
Once the application is running, visit:
```
http://localhost:8080/swagger-ui.html
```

## Testing

### Run Architecture Tests
```bash
./mvnw test -pl app
```

These tests validate module boundaries and dependencies using Spring Modulith.

## Technologies Used

- **Spring Boot 3.3.0**
- **Spring Modulith 1.2.1**
- **Spring Data JPA**
- **H2 Database** (for demo purposes)
- **SpringDoc OpenAPI** (Swagger UI)

## License

MIT
