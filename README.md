# m42-spring-service-template

Reusable Java 21 Spring Boot starter for Orion services.

## Features

- Spring Boot web, validation, actuator, Prometheus metrics, OpenAPI
- Flyway and PostgreSQL runtime dependencies
- Standard `ApiResponse`
- Global exception handler
- Request correlation filter using `X-Correlation-Id`
- Health controller smoke endpoint
- Testcontainers base class
- Dockerfile and GitHub Actions CI

## Commands

```bash
mvn test
mvn spring-boot:run
docker build -t m42-spring-service-template .
```
