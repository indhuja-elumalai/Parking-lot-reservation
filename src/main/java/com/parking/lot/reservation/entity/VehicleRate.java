package com.parking.lot.reservation.entity;

import com.parking.lot.reservation.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class VehicleRate {
    @Id
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private double hourlyRate;

    public VehicleRate(VehicleType vehicleType, double hourlyRate) {
        this.vehicleType = vehicleType;
        this.hourlyRate = hourlyRate;
    }
}