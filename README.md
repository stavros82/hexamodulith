# Hexamodulith

A starter template for building **modular monoliths** in Java using **Hexagonal Architecture (Ports and Adapters)**.

Hexamodulith provides a clean, maintainable, and scalable foundation for applications that value strong module boundaries, framework-independent business logic, and reliable event-driven communication inside a single deployable unit.

---

## Why this project exists

Many applications start simple and gradually become tightly coupled to frameworks, persistence concerns, and delivery mechanisms. Over time, this makes the codebase harder to test, evolve, and reason about.

Hexamodulith is designed to address that problem by offering a template that emphasizes:

- a **clean separation** between domain and infrastructure
- **strict module boundaries** inside a monolith
- **high testability** through dependency inversion
- **reliable event propagation** using the transactional outbox pattern

This repository is intended to be used as a **starter template** for new projects or as a reference implementation for teams adopting a modular monolith architecture.

---

## Core architecture principles

### 1. Hexagonal Architecture

The application follows the principles of **Hexagonal Architecture**, also known as **Ports and Adapters**.

This means:

- the **domain core** contains the business rules
- the core is **independent** of frameworks and external technologies
- interactions with the outside world happen through **ports**
- technical details are implemented in **adapters**

The result is a system where business logic remains stable even when infrastructure changes.

### 2. Modular Monolith

Hexamodulith is a **modular monolith**.

Although the application is deployed as a single unit, it is internally organized into well-defined modules. Each module owns its own responsibilities and exposes only what other modules need.

This approach gives you many of the benefits of microservice-style separation without introducing distributed system complexity too early.

Benefits include:

- lower operational complexity
- stronger internal boundaries
- easier local development and debugging
- clearer ownership of business capabilities

### 3. Dependency Rule

Dependencies always point **inward**.

This is one of the most important rules in the project:

- infrastructure depends on application/core
- adapters depend on ports
- the domain does **not** depend on frameworks, databases, or UI layers

By protecting this rule, the system remains easier to test, refactor, and maintain over time.

---

## Transactional Outbox Pattern via AOP

Hexamodulith includes the **Transactional Outbox Pattern** to support reliable event propagation between modules.

### The problem it solves

When an application both:
1. updates the database, and
2. publishes an event,

it risks a **dual-write problem**.

For example:

- the database update succeeds, but event publishing fails
- the event is published, but the transaction is rolled back

Either outcome creates inconsistency.

### The approach used here

To solve this, domain events are stored in an **outbox table** in the **same transaction** as the business operation.

Later, those events can be safely published asynchronously.

In this project, **Aspect-Oriented Programming (AOP)** is used to automate outbox persistence. This keeps the service layer focused on business use cases instead of mixing in cross-cutting event persistence logic.

### Why this matters

This design improves:

- reliability
- consistency
- separation of concerns
- maintainability of the application layer

It also creates a strong foundation for asynchronous communication between modules without sacrificing transactional safety.

---

## Project goals

This template is intended to help developers build systems that are:

- **cleanly structured**
- **easy to test**
- **easy to extend**
- **resilient to infrastructure change**
- **ready for growth without immediate microservice fragmentation**

---

## Who this template is for

Hexamodulith is a good fit for teams who want:

- a serious architectural starting point for a new Java project
- a modular monolith instead of premature microservices
- a codebase where domain logic is protected from infrastructure concerns
- a practical example of ports and adapters in a real project layout
- a reference for implementing the transactional outbox pattern

---

## Getting started

### Prerequisites

- Java 22
- Maven



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
