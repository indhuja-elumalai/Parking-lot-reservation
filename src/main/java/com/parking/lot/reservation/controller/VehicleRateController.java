package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.entity.VehicleRate;
import com.parking.lot.reservation.service.VehicleRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rates")
public class VehicleRateController {

    private final VehicleRateService vehicleRateService;

    public VehicleRateController(VehicleRateService vehicleRateService) {
        this.vehicleRateService = vehicleRateService;
    }

    @PostMapping
    public ResponseEntity<VehicleRate> createRate(@RequestBody VehicleRate vehicleRate) {
        VehicleRate createdRate = vehicleRateService.createRate(vehicleRate);
        return new ResponseEntity<>(createdRate, HttpStatus.CREATED);
    }
}