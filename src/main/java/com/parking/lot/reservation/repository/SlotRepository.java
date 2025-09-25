package com.parking.lot.reservation.repository;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    
    // Custom finder method to check if a slot already exists on a specific floor
    Optional<Slot> findBySlotNumberAndFloor(String slotNumber, Floor floor);
}