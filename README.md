# Hybrid Automation Framework

This is a Java-based hybrid automation framework built using Selenium WebDriver, TestNG, Maven, Apache POI, Extent Reports, and Log4j2.

## Framework Features

- Selenium WebDriver UI automation
- Maven-based dependency and build management
- TestNG test execution
- Page Object Model design pattern
- Data-driven testing using Excel
- Config-driven execution using properties file
- Environment-based URL handling
- Command-line config overrides
- ThreadLocal WebDriver for parallel execution
- ThreadLocal ExtentTest for parallel-safe reporting
- Extent Reports with screenshot support
- Log4j2 console and file logging
- Explicit wait utility using WebDriverWait
- Smoke and regression TestNG suites
- Retry analyzer for failed tests
- Parallel execution using TestNG
- API validation layer using Rest Assured
- DB validation layer using JDBC
- Cucumber BDD integration

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Apache POI
- Extent Reports
- Log4j2
- Rest Assured
- JDBC
- Cucumber BDD
- MySQL JDBC Driver

## Project Structure

```text
HybridFramework
├── pom.xml
├── README.md
├── testng-smoke.xml
├── testng-regression.xml
├── testng-db.xml
├── testng-cucumber.xml
├── reports
├── logs
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── api
│   │   │   ├── base
│   │   │   ├── browserFactory
│   │   │   ├── dataProvider
│   │   │   ├── db
│   │   │   ├── helper
│   │   │   ├── listeners
│   │   │   └── pages
│   │   └── resources
│   │       ├── config
│   │       │   └── config.properties
│   │       └── log4j2.xml
│   └── test
│       ├── java
│       │   ├── apiTests
│       │   ├── dbTests
│       │   ├── hooks
│       │   ├── runners
│       │   ├── stepdefinitions
│       │   └── testcases
│       └── resources
│           ├── features
│           │   └── login.feature
│           └── testdata
│               └── testData.xlsx

Important Packages
base
Contains common setup and teardown logic.

BaseClass starts the browser before each TestNG test and closes/removes the driver after each test.

browserFactory
Contains browser creation and driver management logic.

BrowserFactory uses ThreadLocal<WebDriver> so each parallel test thread gets its own isolated browser instance.

pages
Contains Page Object Model classes.

Page classes store locators and reusable UI actions. Test classes call page methods instead of directly using Selenium locators.

dataProvider
Contains utilities to read config properties and Excel test data.

ConfigReader reads values from config.properties and also supports Maven command-line overrides using -D.

ExcelReader reads test data from Excel using Apache POI.

helper
Contains reusable utility classes.

Examples:

WaitUtils for explicit waits
Utilities for screenshots and common helper methods
listeners
Contains TestNG listeners and execution helpers.

Examples:

Extent report listener
Retry analyzer
Annotation transformer for global retry setup
api
Contains reusable API automation logic using Rest Assured.

Examples:

API endpoint constants
Request specification builder
Common GET, POST, PUT, DELETE methods
API response validation methods
db
Contains reusable DB validation logic using JDBC.

Examples:

DB connection factory
Common DB utility methods
Centralized SQL query constants
Configuration
Config file location:

src/main/resources/config/config.properties
Example:

env=qa
browser=Chrome
headless=false

qa.url=https://ineuron-courses.vercel.app/login
stage.url=https://ineuron-courses.vercel.app/login
prod.url=https://ineuron-courses.vercel.app/login

api.baseUri=https://ineuron-courses.vercel.app

db.url=jdbc:mysql://localhost:3306/testdb
db.username=root
db.password=password
Command-Line Overrides
The framework supports Maven command-line overrides.

Examples:

mvn test -Denv=stage
mvn test -Dheadless=true
mvn test -Dbrowser=Chrome
mvn test -DappUrl=https://ineuron-courses.vercel.app/login
mvn test -Dapi.baseUri=https://example-api.com
mvn test -Ddb.url=jdbc:mysql://host:3306/dbname -Ddb.username=myuser -Ddb.password=mypassword
Priority:

1. Maven command-line system property
2. config.properties value
How To Run
Run default suite:

mvn test
Run smoke suite:

mvn test -DsuiteXmlFile=testng-smoke.xml
Run regression suite:

mvn test -DsuiteXmlFile=testng-regression.xml
Run DB suite:

mvn test -DsuiteXmlFile=testng-db.xml
Run Cucumber suite:

mvn test -DsuiteXmlFile=testng-cucumber.xml
Run in headless mode:

mvn test -Dheadless=true
Run on a specific environment:

mvn test -Denv=stage
Run with direct application URL override:

mvn test -DappUrl=https://ineuron-courses.vercel.app/login
Run regression suite in headless mode:

mvn test -DsuiteXmlFile=testng-regression.xml -Dheadless=true
Run DB suite with DB credentials:

mvn test -DsuiteXmlFile=testng-db.xml -Ddb.url=jdbc:mysql://host:3306/dbname -Ddb.username=myuser -Ddb.password=mypassword
TestNG Suites
Smoke Suite
File:

testng-smoke.xml
Purpose:

Runs critical smoke tests.
Regression Suite
File:

testng-regression.xml
Purpose:

Runs regression tests, including UI/API tests based on configured groups.
DB Suite
File:

testng-db.xml
Purpose:

Runs DB validation tests separately because they depend on external database availability.
Cucumber Suite
File:

testng-cucumber.xml
Purpose:

Runs Cucumber BDD scenarios using TestNG runner.
API Layer
Reusable API classes are stored under:

src/main/java/api
API tests are stored under:

src/test/java/apiTests
API package contains:

APIEndpoints.java
APIClient.java
RequestBuilder.java
ResponseValidator.java
The API base URI is configured using:

api.baseUri=https://ineuron-courses.vercel.app
It can be overridden from command line:

mvn test -DsuiteXmlFile=testng-regression.xml -Dapi.baseUri=https://example-api.com
DB Layer
Reusable DB classes are stored under:

src/main/java/db
DB tests are stored under:

src/test/java/dbTests
DB package contains:

DBConnectionFactory.java
DBUtils.java
Queries.java
DB configuration:

db.url=jdbc:mysql://localhost:3306/testdb
db.username=root
db.password=password
Real DB credentials should not be committed to Git.

Use Maven command-line values or Jenkins credentials:

mvn test -DsuiteXmlFile=testng-db.xml -Ddb.url=jdbc:mysql://host:3306/dbname -Ddb.username=myuser -Ddb.password=mypassword
DB tests are kept in a separate suite because they depend on database availability.

Cucumber BDD
Feature files are stored under:

src/test/resources/features
Step definitions are stored under:

src/test/java/stepdefinitions
Hooks are stored under:

src/test/java/hooks
Runner is stored under:

src/test/java/runners
Run Cucumber suite:

mvn test -DsuiteXmlFile=testng-cucumber.xml
Cucumber reports are generated under:

target/cucumber-reports
Reports And Logs
Extent reports are generated under:

reports
Log file is generated at:

logs/automation.log
TestNG/Surefire reports are generated under:

target/surefire-reports
Cucumber reports are generated under:

target/cucumber-reports
Jenkins Integration
The framework is Maven-based and can be executed from Jenkins using Maven commands.

Example smoke execution:

mvn clean test -DsuiteXmlFile=testng-smoke.xml -Dheadless=true
Example regression execution:

mvn clean test -DsuiteXmlFile=testng-regression.xml -Dheadless=true
Example Cucumber execution:

mvn clean test -DsuiteXmlFile=testng-cucumber.xml -Dheadless=true
Example DB execution with parameters:

mvn clean test -DsuiteXmlFile=testng-db.xml -Ddb.url=jdbc:mysql://host:3306/dbname -Ddb.username=myuser -Ddb.password=mypassword
For DB execution, credentials should be passed securely through Jenkins credentials instead of being hardcoded in config files.

Git Ignore Recommendation
Generated files should not be committed to Git.

Examples:

target/
test-output/
reports/
logs/
Screenshots/
ElementScreenshot/
.DS_Store
Source code, resources, TestNG XML files, README, and Maven configuration should be committed.

Interview Explanation
This framework follows a hybrid automation design because it combines:

Page Object Model
Data-driven testing
Config-driven execution
TestNG execution
Cucumber BDD
API validation
DB validation
Reusable utilities
Reporting
Logging
Retry logic
Parallel execution
CI/CD readiness
The framework uses ThreadLocal<WebDriver> so each parallel test thread gets its own isolated browser instance.

It also uses ThreadLocal<ExtentTest> so logs and screenshots do not mix during parallel execution.

The framework supports multiple execution modes:

UI smoke tests
UI/API regression tests
DB validation tests
Cucumber BDD scenarios
Headless CI/CD execution
Environment-based execution
Command-line override execution
