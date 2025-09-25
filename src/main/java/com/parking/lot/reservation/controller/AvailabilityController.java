package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.enums.VehicleType;
import com.parking.lot.reservation.service.AvailabilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public ResponseEntity<Page<Slot>> getAvailableSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam(required = false) VehicleType vehicleType,
            @PageableDefault(size = 10, page = 0, sort = "slotNumber") Pageable pageable) {
        
        // This is a crucial check to prevent a NullPointerException if no vehicleType is provided.
        // The previous error was a result of Spring failing to handle the parameter conversion.
        if (vehicleType != null) {
            Page<Slot> availableSlots = availabilityService.getAvailableSlots(startTime, endTime, vehicleType, pageable);
            return ResponseEntity.ok(availableSlots);
        } else {
            // If no vehicleType is specified, we return an empty page to the user.
            return ResponseEntity.ok(Page.empty(pageable));
        }
    }
}