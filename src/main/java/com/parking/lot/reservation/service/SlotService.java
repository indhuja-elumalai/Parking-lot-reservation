package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.entity.Slot;
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

    public Slot createSlot(Slot slot) {
        Floor floor = floorRepository.findById(slot.getFloor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Floor not found"));
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