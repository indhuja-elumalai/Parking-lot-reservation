package com.parking.lot.reservation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title = "Parking Lot Reservation API",
        version = "1.0",
        description = "API for managing parking slots and reservations."
    )
)
@SpringBootApplication
public class ParkingLotReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingLotReservationApplication.class, args);
    }
}
