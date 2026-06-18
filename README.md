# SpotQuote API Automation

![API Tests](https://github.com/Meharukjabeen3/SpotQuote-API-Automation/actions/workflows/api-tests.yml/badge.svg)

A REST API test automation framework built with Java, RestAssured, and TestNG — covering full CRUD operations, authentication, data-driven testing, and HTML reporting.

## Tech Stack

| Tool | Purpose |
|---|---|
| Java 17 | Core language |
| RestAssured 5.4 | HTTP client and API assertions |
| TestNG 7.9 | Test runner and data-driven testing |
| ExtentReports 5 | HTML test reporting |
| Maven | Build and dependency management |
| GitHub Actions | CI/CD pipeline |

## Project Structure

```
SpotQuote-API-Automation/
├── src/test/java/
│   ├── base/
│   │   └── BaseTest.java          # RestAssured setup, base URI, auth header
│   ├── models/
│   │   ├── CreateUserRequest.java # POJO for POST /users request
│   │   ├── CreateUserResponse.java# POJO for POST /users response
│   │   ├── LoginRequest.java      # POJO for POST /login request
│   │   └── LoginResponse.java     # POJO for POST /login response
│   ├── testcases/
│   │   ├── GetUserTests.java      # GET /users — single user, list, response time
│   │   ├── CreateUserTests.java   # POST /users — create and validate
│   │   ├── UpdateUserTests.java   # PUT/PATCH /users/{id} — full and partial update
│   │   ├── DeleteUserTests.java   # DELETE /users/{id} — 204 and empty body
│   │   └── LoginTests.java        # POST /login — valid, invalid, data-driven
│   └── utilities/
│       ├── ConfigReader.java      # Loads environment config files
│       ├── ExtentReportManager.java # Initialises ExtentReports
│       └── TestListener.java      # TestNG listener — logs pass/fail to report
├── config/
│   └── qa.properties.example      # Config template (copy to qa.properties)
├── .github/workflows/
│   └── api-tests.yml              # GitHub Actions CI pipeline
└── testng.xml                     # TestNG suite definition
```

## Test Coverage

| Test Class | Tests | Endpoints |
|---|---|---|
| GetUserTests | 4 | GET /users/{id}, GET /users |
| CreateUserTests | 2 | POST /users |
| UpdateUserTests | 2 | PUT /users/{id}, PATCH /users/{id} |
| DeleteUserTests | 2 | DELETE /users/{id} |
| LoginTests | 5 | POST /login (valid + data-driven invalid) |
| **Total** | **15** | |

## Setup

**Prerequisites:** Java 17+, Maven 3.6+

1. Clone the repository
   ```
   git clone https://github.com/Meharukjabeen3/SpotQuote-API-Automation.git
   ```

2. Create your config file from the example
   ```
   cp config/qa.properties.example config/qa.properties
   ```

3. Add your free API key from [app.reqres.in/api-keys](https://app.reqres.in/api-keys) to `config/qa.properties`
   ```
   api.key=YOUR_KEY_HERE
   ```

4. Run the tests
   ```
   mvn clean test
   ```

5. Open the HTML report
   ```
   reports/TestReport.html
   ```

## Running Against Different Environments

```
mvn clean test -Denv=staging
```

## CI/CD

Every push to `master` triggers the GitHub Actions pipeline which:
- Spins up Ubuntu with Java 17
- Runs all 15 tests
- Uploads the ExtentReport HTML as a downloadable artifact
