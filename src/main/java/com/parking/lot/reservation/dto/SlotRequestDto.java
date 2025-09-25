package com.parking.lot.reservation.dto;

import com.parking.lot.reservation.enums.VehicleType;

/**
 * Data Transfer Object (DTO) for creating a new Slot.
 * This class is used to map the incoming JSON request body,
 * providing a clean way to handle request data without exposing the
 * internal entity model directly.
 */
public class SlotRequestDto {

    private String slotNumber;
    private VehicleType vehicleType;
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
