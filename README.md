

# Parking Lot Reservation System

## Project Description

This is a backend application for a parking lot reservation system. It provides a robust and reliable RESTful API to manage parking floors, slots, and reservations. The system is designed to handle common parking lot scenarios, including checking for available slots, creating reservations, and ensuring business rules are strictly followed.

## Key Features

The application's API offers the following core functionalities:

  * **Slot Management**: Create, view, and delete parking slots and floors.
  * **Reservation System**: Create, view, and cancel parking reservations.
  * **Availability Check**: Find all available parking slots within a specific date and time range.
  * **Automatic Assignment**: Automatically assign an available slot based on a vehicle's type (`TWO_WHEELER` or `FOUR_WHEELER`) and the requested time.
  * **Pricing**: Calculate the total cost of a reservation based on a configurable hourly rate per vehicle type.
  * **Business Rules & Validation**: The API enforces key business rules to prevent data inconsistencies:
      * Reservations are limited to a maximum duration of 24 hours.
      * The reservation's `startTime` must occur before its `endTime`.
      * Partial hours are rounded up and charged as a full hour.
      * A reservation's vehicle type must match the assigned slot's vehicle type.
      * Double-booking is prevented by checking for conflicting reservations.

## Technologies Used

  * **Backend**: Spring Boot 3.x
  * **Language**: Java 17+
  * **Database**: PostgreSQL
  * **Build Tool**: Maven
  * **API**: RESTful API with JSON data format

## Getting Started

### Prerequisites

  * Java Development Kit (JDK) 17 or higher
  * Apache Maven
  * PostgreSQL database instance

### How to Run

1.  **Clone the repository** from GitHub.
2.  **Configure the database connection** in your `src/main/resources/application.properties` file.
3.  **Build the project** using Maven:
    ```sh
    mvn clean install
    ```
4.  **Run the application** from the `target` directory:
    ```sh
    java -jar parking-lot-reservation.jar
    ```
5.  The application will be running on `http://localhost:8080`.

## API Endpoints

| Endpoint                                       | Method | Description                                            |
| ---------------------------------------------- | ------ | ------------------------------------------------------ |
| `/api/floors`                                  | `POST` | Create a new parking floor.                            |
| `/api/floors`                                  | `GET`  | Get all parking floors.                                |
| `/api/slots`                                   | `POST` | Create a new parking slot.                             |
| `/api/slots`                                   | `GET`  | Get all parking slots.                                 |
| `/api/availability`                            | `GET`  | Find all available slots in a time range.              |
| `/api/reservations`                            | `POST` | Create a reservation (auto-assigns slot if none provided). |
| `/api/reservations/{id}`                       | `GET`  | Get details for a specific reservation.                |
| `/api/reservations/{id}`                       | `DELETE`| Cancel a reservation by ID.                            |
| `/api/slots/{id}`                              | `DELETE`| Delete a slot by ID.                                   |
| `/api/floors/{id}`                             | `DELETE`| Delete a floor by ID.                                  |