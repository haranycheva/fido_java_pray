# Project info

A Spring Boot RESTful web service for creating, reading, updating, deleting, and searching quotes with pagination, validation, and Swagger OpenAPI 3 documentation.

# Getting Started

## 1. Clone the repository

`git clone https://github.com/haranycheva/fido_java_pray`

`cd fido_java_pray`


## 2. Build the project

### Environment Setup

Before running the application, set up the required environment variables or create an `application-local.properties` file in `src/main/resources/`:

```properties
DB_URL=jdbc:postgresql://<HOST>:5432/postgres
DB_USERNAME=<YOUR_DB_USER>
DB_PASSWORD=<YOUR_DB_PASSWORD>

-- Local development options
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


### Build the project

`./mvnw clean package -DskipTests`


## 3. Run the application

`./mvnw spring-boot:run`


The application will start on http://localhost:8080.

## API Documentation (Swagger UI)

Once the application is running, access the interactive Swagger documentation in your browser:

Swagger UI: http://localhost:8080/swagger-ui/index.html

OpenAPI Schema (JSON): http://localhost:8080/v3/api-docs