package com.parking.lot.reservation.controller;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.service.FloorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @PostMapping
    public ResponseEntity<Floor> createFloor(@RequestBody Floor floor) {
        return new ResponseEntity<>(floorService.createFloor(floor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Floor>> getAllFloors() {
        return ResponseEntity.ok(floorService.getAllFloors());
    }
    
    // New method to delete a specific floor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFloorById(@PathVariable Long id) {
        floorService.deleteFloorById(id);
        return ResponseEntity.noContent().build();
    }
    
    // New method to delete all floors
    @DeleteMapping
    public ResponseEntity<Void> deleteAllFloors() {
        floorService.deleteAllFloors();
        return ResponseEntity.noContent().build();
    }
}