package com.parking.lot.reservation.dto;

import com.parking.lot.reservation.enums.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) for creating a new Slot.
 * This class is used to map the incoming JSON request body,
 * providing a clean way to handle request data without exposing the
 * internal entity model directly.
 */
@Schema(description = "Request DTO for creating a new parking slot")
public class SlotRequestDto {

    @Schema(description = "The unique number or identifier for the parking slot", example = "A-101")
    private String slotNumber;

    @Schema(description = "The type of vehicle the slot is designed for", example = "CAR")
    private VehicleType vehicleType;

    @Schema(description = "The ID of the floor where the slot is located", example = "1")
    private Long floorId;

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
}
