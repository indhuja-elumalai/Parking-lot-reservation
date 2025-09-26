package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.repository.FloorRepository;
import com.parking.lot.reservation.exception.FloorAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FloorService {

    private final FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Floor createFloor(Floor floor) {
        Optional<Floor> existingFloor = floorRepository.findByName(floor.getName());
        if (existingFloor.isPresent()) {
            throw new FloorAlreadyExistsException("Floor with name '" + floor.getName() + "' already exists.");
        }
        return floorRepository.save(floor);
    }

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }
    
    public void deleteFloorById(Long id) {
        floorRepository.deleteById(id);
    }
    
    public void deleteAllFloors() {
        floorRepository.deleteAll();
    }
}