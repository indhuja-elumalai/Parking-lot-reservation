package com.parking.lot.reservation.dto;

import com.parking.lot.reservation.enums.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "Data Transfer Object for creating a new reservation")
public class ReservationRequestDto {

    @Schema(description = "The ID of the parking slot to reserve", example = "101")
    private Long slotId;

    @Schema(description = "The license plate number of the vehicle", example = "KA-01-AB-1234")
    @NotBlank
    private String vehicleNumber;

    @Schema(description = "The type of vehicle for the reservation", example = "CAR")
    @NotNull
    private VehicleType vehicleType;

    @Schema(description = "The start time of the reservation", example = "2025-10-26T10:00:00")
    @NotNull
    @Future
    private LocalDateTime startTime;

    @Schema(description = "The end time of the reservation", example = "2025-10-26T12:00:00")
    @NotNull
    @Future
    private LocalDateTime endTime;

    // This field is used for optimistic locking
    private Long version;

    // Getters and Setters
    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
