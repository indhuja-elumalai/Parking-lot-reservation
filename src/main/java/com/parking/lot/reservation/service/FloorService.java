package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.repository.FloorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {

    private final FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Floor createFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }
    
    // New method to delete a specific floor
    public void deleteFloorById(Long id) {
        floorRepository.deleteById(id);
    }
    
    // New method to delete all floors
    public void deleteAllFloors() {
        floorRepository.deleteAll();
    }
}