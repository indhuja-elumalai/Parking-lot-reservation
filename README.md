# 🅿️ Parking Lot Reservation System (Backend REST API)

## 1. Project Overview

This project implements a backend REST API for a modern Parking Lot Reservation system using **Java** and **Spring Boot**. The system allows administrators to manage floors and slots, while providing customers the ability to reserve parking for specific, conflict-free time ranges. The solution is designed with clean architecture, adhering to best practices, and demonstrating robust business logic implementation.

## 2. Technical Stack and Dependencies

| Component | Technology | Version / Purpose |
| :--- | :--- | :--- |
| **Language** | Java | 17+ (LTS) |
| **Framework** | Spring Boot | 3.2.x |
| **Database** | PostgreSQL | Relational storage for entities (Floors, Slots, Reservations) |
| **API Docs** | Springdoc-OpenAPI | Enables Swagger UI (Bonus Feature) |
| **Utility** | Project Lombok | Boilerplate code reduction (`@Data`, `@Builder`, etc.) |
| **Build Tool** | Maven | Dependency Management and Build Lifecycle |
| **Validation** | Spring Validation | Jakarta Bean Validation (`@Valid`, Custom Regex) |
| **Testing** | Postman | Unit testing with high coverage goal |

## 3. Implemented Features and API Endpoints

The API provides the following endpoints as required by the assignment, with full transactional control and validation.

### Management Endpoints (Admin)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/api/v1/floors` | Creates a new parking floor. |
| **POST** | `/api/v1/slots` | Creates a specified number of parking slots for a given floor. |

### Customer Reservation Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/api/v1/reserve` | Creates a new reservation, checking for availability and calculating the final cost. |
| **GET** | `/api/v1/availability` | Lists all available slots for a specified time range. |
| **GET** | `/api/v1/reservations/{id}` | Fetches the full details of a specific reservation. |
| **DELETE** | `/api/v1/reservations/{id}` | Cancels an existing reservation. |

## 4. Business Logic and Validation Implementation

All specified business rules have been rigorously enforced through a combination of Bean Validation and Service Layer logic.

| Feature | Implementation Detail |
| :--- | :--- |
| **Availability Check** | Implemented within the `ReservationService` using a database query to check for **no overlapping reservations** for the requested slot ID within the given time range (`startTime` and `endTime`). |
| **Fee Calculation** | Cost is calculated in the `ReservationService`. The hourly rates are configurable, and the duration is calculated precisely. |
| **Partial Hour Rule** | The `Duration` is rounded **up** to the nearest whole hour before calculating the total fee, ensuring 1.2 hours is charged as 2 hours. |
| **Duration Limit** | Validation ensures `endTime` is after `startTime` and the total reservation duration **does not exceed 24 hours**. |
| **Vehicle Number Format** | A custom Regular Expression (regex) pattern is applied using `@Pattern` in the DTO to enforce the `XX00XX0000` format (e.g., `KA05MH1234`). |
| **Rate Configurability** | Hourly rates for different vehicle types (2-wheeler, 4-wheeler) are defined in a central configuration (e.g., database or service constant/enum) to allow easy future changes and new vehicle types. |

## 5. Architectural and Design Choices

The project leverages key Spring Boot capabilities and design patterns to ensure maintainability, testability, and robustness.

* **Layered Architecture:** Clear separation of concerns (Controller, Service, Repository) allows for highly modular and testable code.
* **Data Transfer Objects (DTOs):** DTOs are used for all API communication (requests and responses) to decouple the API contract from the JPA entities.
* **Global Exception Handling:** Implemented using **`@ControllerAdvice`** and **`@ExceptionHandler`** to catch validation errors and custom exceptions (e.g., `SlotUnavailableException`, `ReservationNotFoundException`) and return clean, standardized JSON error responses.
* **Bean Validation:** Standard JSR 380 annotations (`@NotNull`, `@Future`, `@Pattern`, etc.) are used on DTO fields and automatically triggered by **`@Valid`** in the controllers.

## 6. Bonus Features Implemented

| Bonus Feature | Implementation |
| :--- | :--- |
| **API Documentation** | **Swagger UI (using Springdoc-OpenAPI)** is enabled. The interactive documentation is available at the `/swagger-ui.html` endpoint on the running server, allowing for easy testing and exploration of all API contracts. |

## 7. Setup and Running Instructions

### Prerequisites

* **Java Development Kit (JDK):** Version 17+
* **PostgreSQL Database:** A running instance is required for persistence.
* **Database Configuration:** Update the `src/main/resources/application.properties` file with your PostgreSQL connection details (URL, username, password).

### Local Execution

1.  **Clone the Repository:**
    ```bash
    git clone [YOUR_GITHUB_REPOSITORY_LINK]
    cd parking-lot-reservation
    ```

2.  **Build the Project:**
    ```bash
    ./mvnw clean install
    ```

3.  **Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Access the API:**
    The API will be available at `http://localhost:8080`.
    Access the interactive documentation (Swagger UI) at:
    `http://localhost:8080/swagger-ui.html`

## 8. Submission Details

| Item | Status / Placeholder |
| :--- | :--- |
| **GitHub Repository** | https://github.com/indhuja-elumalai/Parking-lot-reservation.git |
| **Swagger UI Documentation** | http://localhost:8080/swagger-ui/index.html |
| **Deployed Application URL** | [INSERT RENDER / CLOUD RUN LIVE URL HERE] |
| **Test Screenshots** | [Link to Google Drive folder with Postman/Swagger/Unit Test Screenshots] |
```eof
