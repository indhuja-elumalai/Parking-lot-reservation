package com.parking.lot.reservation.service;

import com.parking.lot.reservation.dto.SlotRequestDto;
import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.exception.ResourceNotFoundException;
import com.parking.lot.reservation.exception.SlotAlreadyReservedException;
import com.parking.lot.reservation.repository.FloorRepository;
import com.parking.lot.reservation.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final FloorRepository floorRepository;

    public SlotService(SlotRepository slotRepository, FloorRepository floorRepository) {
        this.slotRepository = slotRepository;
        this.floorRepository = floorRepository;
    }

    public Slot createSlot(SlotRequestDto slotRequestDto) {
        Floor floor = floorRepository.findById(slotRequestDto.getFloorId())
                .orElseThrow(() -> new ResourceNotFoundException("Floor not found with ID: " + slotRequestDto.getFloorId()));

        if (slotRepository.findBySlotNumberAndFloor(slotRequestDto.getSlotNumber(), floor).isPresent()) {
            throw new SlotAlreadyReservedException("Slot with number " + slotRequestDto.getSlotNumber() + " already exists on this floor.");
        }

        Slot slot = new Slot();
        slot.setSlotNumber(slotRequestDto.getSlotNumber());
        slot.setVehicleType(slotRequestDto.getVehicleType());
        slot.setFloor(floor);
        return slotRepository.save(slot);
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    // New method to delete a specific slot by ID
    public void deleteSlotById(Long id) {
        slotRepository.deleteById(id);
    }

    // New method to delete all slots
    public void deleteAllSlots() {
        slotRepository.deleteAll();
    }
}
