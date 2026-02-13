### 1. Prerequisites
* **Java 25 SDK**
* **Docker & Docker Compose**
* **Gradle 8.x+** (or use the included `./gradlew` wrapper)

### 2. Infrastructure Setup
Spin up the isolated PostgreSQL database using Docker:
```bash
docker-compose up -d

