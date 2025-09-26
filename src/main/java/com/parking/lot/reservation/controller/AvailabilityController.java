package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.service.AvailabilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public ResponseEntity<Page<Slot>> findAllAvailable(
            // Use Boolean check to prevent empty strings from being treated as valid.
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime,
            @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {

        // Check for the presence of both parameters to determine which service method to call.
        if (startTime != null && endTime != null) {
            // Case 1: Times are provided, perform time-based availability check with pagination.
            Page<Slot> availableSlots = availabilityService.findAvailableSlots(startTime, endTime, pageable);
            return ResponseEntity.ok(availableSlots);
        } else {
            // Case 2: No times are provided, return all slots WITH PAGINATION.
            Page<Slot> allSlots = availabilityService.findAll(pageable);
            return ResponseEntity.ok(allSlots);
        }
    }
}
