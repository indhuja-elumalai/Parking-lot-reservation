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
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam(required = false) VehicleType vehicleType,
            @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {

        // Check if both startTime and endTime are provided for a time-based search
        if (startTime != null && endTime != null) {
            // If a vehicle type is also specified, perform a more specific search
            if (vehicleType != null) {
                Page<Slot> availableSlots = availabilityService.findAvailableSlots(startTime, endTime, vehicleType, pageable);
                return ResponseEntity.ok(availableSlots);
            } else {
                // If only times are specified, find all available slots regardless of vehicle type
                Page<Slot> availableSlots = availabilityService.findAvailableSlots(startTime, endTime, pageable);
                return ResponseEntity.ok(availableSlots);
            }
        } else {
            // If no times are provided, return all slots with pagination and sorting
            Page<Slot> allSlots = availabilityService.findAll(pageable);
            return ResponseEntity.ok(allSlots);
        }
    }
}