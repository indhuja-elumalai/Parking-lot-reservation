package com.parking.lot.reservation.repository;

import com.parking.lot.reservation.entity.Floor;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    
    // Custom finder method to check if a slot already exists on a specific floor
    Optional<Slot> findBySlotNumberAndFloor(String slotNumber, Floor floor);

    // New method for pagination and sorting
    @Query("SELECT s FROM Slot s " +
           "WHERE s.id NOT IN :reservedSlotIds " +
           "AND (:vehicleType IS NULL OR s.vehicleType = :vehicleType)")
    Page<Slot> findAvailableSlots(@Param("reservedSlotIds") Set<Long> reservedSlotIds,
                                  @Param("vehicleType") VehicleType vehicleType,
                                  Pageable pageable);
}