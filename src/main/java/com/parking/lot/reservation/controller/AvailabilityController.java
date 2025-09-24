package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.service.AvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public ResponseEntity<List<Slot>> findAllAvailable(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {

        List<Slot> availableSlots = availabilityService.findAllAvailable(startTime, endTime);
        return ResponseEntity.ok(availableSlots);
    }
}