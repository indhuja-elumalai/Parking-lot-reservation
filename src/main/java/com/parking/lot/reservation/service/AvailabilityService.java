package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.Reservation;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.enums.VehicleType;
import com.parking.lot.reservation.repository.ReservationRepository;
import com.parking.lot.reservation.repository.SlotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    private final SlotRepository slotRepository;
    private final ReservationRepository reservationRepository;

    public AvailabilityService(SlotRepository slotRepository, ReservationRepository reservationRepository) {
        this.slotRepository = slotRepository;
        this.reservationRepository = reservationRepository;
    }

    public Page<Slot> findAvailableSlots(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable) {
        List<Reservation> conflictingReservations = reservationRepository.findAllConflictingReservations(startTime, endTime);

        Set<Long> reservedSlotIds = conflictingReservations.stream()
                .map(reservation -> reservation.getSlot().getId())
                .collect(Collectors.toSet());

        return slotRepository.findByIdNotIn(reservedSlotIds, pageable);
    }
    
    public Page<Slot> findAll(Pageable pageable) {
        return slotRepository.findAll(pageable);
    }

    public Slot findAvailableSlotForVehicle(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType) {
        List<Reservation> conflictingReservations = reservationRepository.findAllConflictingReservations(startTime, endTime);

        Set<Long> reservedSlotIds = conflictingReservations.stream()
                .map(reservation -> reservation.getSlot().getId())
                .collect(Collectors.toSet());

        return slotRepository.findAll().stream()
                .filter(slot -> !reservedSlotIds.contains(slot.getId()) && slot.getVehicleType() == vehicleType)
                .findFirst()
                .orElse(null);
    }
    
    // New method for pagination and sorting
    public Page<Slot> getAvailableSlots(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType, Pageable pageable) {
        // Log the input parameters to debug the issue
        logger.info("Debugging getAvailableSlots - startTime: {}, endTime: {}, vehicleType: {}, pageable: {}", startTime, endTime, vehicleType, pageable);

        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(startTime, endTime);
        Set<Long> reservedSlotIds = overlappingReservations.stream()
                .map(reservation -> reservation.getSlot().getId())
                .collect(Collectors.toSet());
        
        // Log the reserved slot IDs to see if any are being filtered out
        logger.info("Debugging getAvailableSlots - Reserved Slot IDs: {}", reservedSlotIds);

        return slotRepository.findAvailableSlots(reservedSlotIds, vehicleType, pageable);
    }
}