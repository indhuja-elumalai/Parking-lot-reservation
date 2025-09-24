package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Slot> createSlot(@RequestBody Slot slot) {
        return new ResponseEntity<>(slotService.createSlot(slot), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        return ResponseEntity.ok(slotService.getAllSlots());
    }

    // New method to delete a specific slot
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlotById(@PathVariable Long id) {
        slotService.deleteSlotById(id);
        return ResponseEntity.noContent().build();
    }

    // New method to delete all slots
    @DeleteMapping
    public ResponseEntity<Void> deleteAllSlots() {
        slotService.deleteAllSlots();
        return ResponseEntity.noContent().build();
    }
}