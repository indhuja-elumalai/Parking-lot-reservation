package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.VehicleRate;
import com.parking.lot.reservation.repository.VehicleRateRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleRateService {

    private final VehicleRateRepository vehicleRateRepository;

    public VehicleRateService(VehicleRateRepository vehicleRateRepository) {
        this.vehicleRateRepository = vehicleRateRepository;
    }

    public VehicleRate createRate(VehicleRate vehicleRate) {
        return vehicleRateRepository.save(vehicleRate);
    }
}