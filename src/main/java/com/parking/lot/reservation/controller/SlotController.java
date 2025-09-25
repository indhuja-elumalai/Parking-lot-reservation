package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.dto.SlotRequestDto;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.service.SlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@Tag(name = "Slot Management", description = "Operations related to parking slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    @Operation(summary = "Create a new parking slot",
               description = "Adds a new parking slot to a specific floor.",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Slot created successfully"),
                   @ApiResponse(responseCode = "409", description = "Slot already exists on this floor")
               })
    public ResponseEntity<Slot> createSlot(@RequestBody SlotRequestDto slotRequestDto) {
        Slot newSlot = slotService.createSlot(slotRequestDto);
        return new ResponseEntity<>(newSlot, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all parking slots",
               description = "Retrieves a list of all existing parking slots.")
    public ResponseEntity<List<Slot>> getAllSlots() {
        List<Slot> slots = slotService.getAllSlots();
        return ResponseEntity.ok(slots);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a parking slot by ID",
               description = "Removes a specific parking slot from the system.",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Slot deleted successfully"),
                   @ApiResponse(responseCode = "404", description = "Slot not found")
               })
    public ResponseEntity<Void> deleteSlotById(@PathVariable Long id) {
        slotService.deleteSlotById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all parking slots",
               description = "Removes all parking slots from the system.")
    public ResponseEntity<Void> deleteAllSlots() {
        slotService.deleteAllSlots();
        return ResponseEntity.noContent().build();
    }
}
