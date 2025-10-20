# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Transaction History Service** - A Spring Boot microservice that provides a readable cache of past transactions from the `ledger-db` database. Part of the Bank of Anthos sample microservices architecture.

**Current State**: Spring Boot 2.3.1 with Java 8
**Target State**: Spring Boot 3.4.4 with Java 17

## Build & Development Commands

### Maven Wrapper Commands
```bash
# Build the project
./mvnw clean compile

# Run tests
./mvnw test

# Run tests with coverage report
./mvnw verify

# Run specific test class
./mvnw test -Dtest=TransactionHistoryControllerTest

# Run checkstyle
./mvnw checkstyle:check

# Build Docker image using Jib
./mvnw compile jib:dockerBuild

# Package application
./mvnw package
```

### Running the Application
```bash
# Run with Spring Boot Maven plugin
./mvnw spring-boot:run

# Required environment variables:
# - VERSION: service version string
# - PORT: webserver port
# - LOCAL_ROUTING_NUM: bank routing number
# - SPRING_DATASOURCE_URL: ledger-db connection URL
# - SPRING_DATASOURCE_USERNAME: database username
# - SPRING_DATASOURCE_PASSWORD: database password
```

## High-Level Architecture

### Core Components

1. **TransactionHistoryController** (`src/main/java/.../TransactionHistoryController.java`)
   - REST API endpoints for transaction retrieval
   - JWT-based authentication for account access
   - Endpoints: `/transactions/{accountId}`, `/ready`, `/healthy`, `/version`

2. **LedgerReader** (`src/main/java/.../LedgerReader.java`)
   - Background polling mechanism that reads new transactions from `ledger-db`
   - Runs on configurable polling interval (default 100ms via `POLL_MS`)
   - Implements health monitoring via `isAlive()` method
   - Uses callback pattern (`LedgerReaderCallback`) to notify on new transactions

3. **TransactionCache** (`src/main/java/.../TransactionCache.java`)
   - Guava LoadingCache configuration for transaction history
   - Cache parameters: size limit (default 1M), expiration (default 60 min)
   - Loads transaction history from `TransactionRepository` on cache miss
   - Limited to `HISTORY_LIMIT` transactions per account (default 100)

4. **TransactionRepository** (`src/main/java/.../TransactionRepository.java`)
   - JPA repository interface for database access
   - Custom queries for finding latest transactions and account history

5. **JWTVerifierGenerator** (`src/main/java/.../JWTVerifierGenerator.java`)
   - JWT token verification using public key from `PUB_KEY_PATH`
   - Authenticates requests to protected transaction endpoints

### Data Flow

```
ledger-db (PostgreSQL)
    ↓ (polling via LedgerReader background thread)
TransactionRepository
    ↓ (on cache miss)
TransactionCache (Guava LoadingCache)
    ↓ (serves cached data)
TransactionHistoryController
    ↓ (REST API)
Clients (with JWT auth)
```

## Migration-Specific Guidance

### Key Migration Requirements (Spring Boot 2.x → 3.x, Java 8 → 17)

1. **Namespace Migration**: All `javax.*` imports must change to `jakarta.*`
   - `javax.persistence.*` → `jakarta.persistence.*`
   - `javax.annotation.*` → `jakarta.annotation.*`
   - `javax.servlet.*` → `jakarta.servlet.*` (if used)

2. **Dependency Version Updates in pom.xml**:
   - Spring Boot parent: `2.3.1.RELEASE` → `3.4.4`
   - Java version: `1.8` → `17`
   - Spring Cloud: `Hoxton.SR5` → `2023.0.0`
   - Spring Cloud GCP: Migrate to standalone `com.google.cloud` libraries
   - Log4j: `2.13.3` → `2.23.1` (security fixes)
   - Guava: `28.2-jre` → latest compatible version
   - JUnit: Ensure JUnit 5 (Jupiter) is used, not JUnit 4
   - Mockito: `2.7.2` → `5.x` for Java 17 compatibility

3. **Code Patterns to Update**:
   - Replace anonymous inner classes with lambdas (LedgerReader.java:85-117)
   - Update deprecated PageRequest API if needed
   - Ensure CacheLoader implements proper generics (TransactionCache.java:63)

4. **Test Migration**:
   - Update `MockitoAnnotations.initMocks()` → `MockitoAnnotations.openMocks()`
   - Ensure JUnit 5 annotations are used (@Test, @BeforeEach, @DisplayName)

### Custom Slash Commands Available

- `/migrate-springboot`: Automate Spring Boot 2.x → 3.x and Java 8 → 17 migration
- `/review-code`: Comprehensive code quality and best practices review
- `/security-review`: OWASP Top 10 security analysis and vulnerability detection

See `.claude/commands/` for detailed command implementations.

## Code Quality Standards

### Checkstyle Configuration
This project uses Sun coding conventions (checkstyle.xml):
- No trailing whitespace
- No tab characters (spaces only)
- Files must end with newline
- Line length limits enforced
- No star imports (`import foo.*`)
- Standard Java naming conventions
- Magic number checks (use constants)

### Testing Standards
- Unit tests use JUnit 5 (Jupiter) and Mockito
- Mock Maker configured in `src/test/resources/mockito-extensions/`
- JaCoCo code coverage reports generated in `target/site/jacoco/`
- Aim for 80%+ coverage on business logic

## Important Project Context

### External Dependencies
- **ledger-db**: PostgreSQL database holding source of truth for transactions
- **JWT Authentication**: Public key mounted at `PUB_KEY_PATH` for token verification
- **GCP Integration**: Spring Cloud GCP for tracing and monitoring

### Environment Configuration
Key environment variables control service behavior:
- `POLL_MS`: Polling frequency for ledger updates (lower = more responsive, higher load)
- `CACHE_SIZE`: Maximum cached account histories (tune based on user base)
- `CACHE_MINUTES`: How long to keep histories cached (balance freshness vs load)
- `HISTORY_LIMIT`: Transaction depth per account (balance completeness vs memory)
- `EXTRA_LATENCY_MILLIS`: Inject artificial latency for testing

### Health & Observability
- `/healthy`: Liveness probe - checks if background LedgerReader thread is alive
- `/ready`: Readiness probe - indicates service is ready to accept requests
- Spring Boot Actuator endpoints enabled
- Log4j2 for structured logging (see `src/main/resources/log4j2.xml`)
- Micrometer + Stackdriver for metrics

## Development Tips

When working on this codebase:
- The LedgerReader background thread is critical - if it dies, the service becomes stale
- Cache invalidation is time-based only - no manual invalidation on updates
- Transaction polling is append-only - assumes monotonically increasing transaction IDs
- JWT verification requires correct public key configuration
- Database connection failures are logged but service remains available (serves stale cache)
