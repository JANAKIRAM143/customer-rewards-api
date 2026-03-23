# customer-rewards-api
Customer Rewards API - For processing customers rewards 

## 📌 Overview

Rewards Web API is a Spring Boot based RESTful service that calculates customer reward points based on purchase transactions.

The application calculates reward points per customer **per month and total for a three-month period**.

This project demonstrates clean architecture, production-ready coding standards, exception handling, unit testing, integration testing, Swagger documentation, and code coverage reporting.

---

## 🎯 Reward Calculation Rules

* Customer earns **2 points** for every dollar spent **above $100**
* Customer earns **1 point** for every dollar spent **between $50 and $100**
* No points for transactions **below $50**

### Example

Transaction Amount = **$120**

Reward Points =

* 2 × (120 − 100) = 40
* 1 × (100 − 50) = 50

**Total Reward Points = 90**

---

## 🧱 Tech Stack

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* H2 In-Memory Database
* Lombok
* Swagger / OpenAPI Documentation
* JUnit 5
* Mockito
* Jacoco (Code Coverage)
* Maven

---

## 🚀 Running the Application

### Clone Repository

```
git clone https://github.com/<your-username>/rewards-web-api.git
cd rewards-web-api
```

### Build Application

```
mvn clean install
```

### Run Application

```
mvn spring-boot:run
```

Application will start on:

```
http://localhost:8080
```

---

## 📘 Swagger API Documentation

After starting the application, access Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

This provides:

* API request/response models
* Endpoint testing
* Response status documentation

---

## 💾 H2 Database Console

```
http://localhost:8080/h2-console
```

JDBC URL:

```
jdbc:h2:mem:testdb
```

---

## 🔗 Sample API Endpoint

### Get Rewards Summary

```
GET /api/rewards
```

### Sample Response

```json
{
  "status": "SUCCESS",
  "message": "Rewards fetched successfully",
  "data": [
    {
      "customerId": 1,
      "monthlyRewards": {
        "JANUARY": 90,
        "FEBRUARY": 120
      },
      "totalRewards": 210
    }
  ]
}
```

---

## ⚠️ Exception Handling

Global exception handler handles:

* Resource Not Found
* Invalid Transaction Amount
* Empty Data Scenarios
* Generic Server Errors

Standard API response wrapper is used across all endpoints.

---

## ✅ Unit Testing

Service layer tests use:

* JUnit 5
* Mockito

Coverage includes:

* Multiple customers
* Multiple transactions
* Negative scenarios
* Empty dataset validation

Run tests:

```
mvn test
```

---

## 📊 Code Coverage Report

Jacoco report available at:

```
target/site/jacoco/index.html
```

---

## 📌 Important Implementation Highlights

* Months are **not hardcoded**
* Transactions grouped dynamically using Java Streams
* Clean layered architecture followed
* JavaDocs added for classes and methods
* Proper naming conventions followed
* Code formatted as per Java standards
* Global API response wrapper implemented
* Swagger annotations added for API and exceptions

---

## 🧪 Integration Testing

SpringBootTest used to validate:

* Application context loading
* API endpoint behaviour
* Repository integration

---

## 👨‍💻 Author

Janakiram
Senior Java Developer

---

