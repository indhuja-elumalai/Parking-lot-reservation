package com.parking.lot.reservation.parking_lot_reservation;

import org.springframework.boot.SpringApplication;

import com.parking.lot.reservation.ParkingLotReservationApplication;

public class TestParkingLotReservationApplication {

	public static void main(String[] args) {
		SpringApplication.from(ParkingLotReservationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
