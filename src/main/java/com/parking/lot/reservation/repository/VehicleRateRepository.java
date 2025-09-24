package com.parking.lot.reservation.repository;

import com.parking.lot.reservation.entity.VehicleRate;
import com.parking.lot.reservation.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRateRepository extends JpaRepository<VehicleRate, VehicleType> {
}