# API Helpers + Stub + Mock Testing Project

This project demonstrates end-to-end API testing using WireMock, Citrus Framework, REST Assured, and Selenoid integration. It includes:

- ✅ WireMock stub server
- ✅ Static frontend for stub testing (served via Jetty)
- ✅ REST and SOAP API helpers (HTTP + Citrus)
- ✅ Optional: SQL + MQ helpers
- ✅ UI test execution via Selenoid
- ✅ JUnit 5 + Citrus test runners
- ✅ JSON Schema validation

---

## ✅ Requirements

- Java 21 (GraalVM-compatible)
- Maven 3.8+
- Docker + Docker Compose (for Selenoid / RabbitMQ)
- Chrome or Chromium installed (for Selenoid UI tests)

---

## 🚀 Local Run Instructions

```bash
# 1. Start WireMock stub server
mvn -q exec:java -Dexec.mainClass=org.otus.stub.StubServer

# 2. Serve the frontend for testing stub API
mvn jetty:run

# 3. Start Selenoid (UI tests via WebDriver)
docker compose -f infra/selenoid-compose.yml up -d

# 4. Run all tests (REST, SOAP, UI, validation)
mvn clean test
