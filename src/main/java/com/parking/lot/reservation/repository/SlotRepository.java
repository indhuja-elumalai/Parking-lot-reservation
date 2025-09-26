package com.parking.lot.reservation.repository;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.entity.Slot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    
    // Custom finder method to check if a slot already exists on a specific floor
    Optional<Slot> findBySlotNumberAndFloor(String slotNumber, Floor floor);

    // New method to find slots whose IDs are not in a given set, with pagination
    Page<Slot> findByIdNotIn(Set<Long> ids, Pageable pageable);
}