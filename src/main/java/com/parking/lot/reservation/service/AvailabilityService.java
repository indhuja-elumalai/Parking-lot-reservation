package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.Reservation;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.enums.VehicleType;
import com.parking.lot.reservation.repository.ReservationRepository;
import com.parking.lot.reservation.repository.SlotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    private final SlotRepository slotRepository;
    private final ReservationRepository reservationRepository;

    public AvailabilityService(SlotRepository slotRepository, ReservationRepository reservationRepository) {
        this.slotRepository = slotRepository;
        this.reservationRepository = reservationRepository;
    }

    // This method handles calls from the controller without a VehicleType.
    public Page<Slot> findAvailableSlots(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable) {
        List<Reservation> conflictingReservations = reservationRepository.findAllConflictingReservations(startTime, endTime);
        Set<Long> reservedSlotIds = conflictingReservations.stream()
                .map(reservation -> reservation.getSlot().getId())
                .collect(Collectors.toSet());
        return slotRepository.findByIdNotIn(reservedSlotIds, pageable);
    }

    // This is the new method that handles calls with a VehicleType.
    public Page<Slot> findAvailableSlots(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType, Pageable pageable) {
        List<Reservation> conflictingReservations = reservationRepository.findAllConflictingReservations(startTime, endTime);
        Set<Long> reservedSlotIds = conflictingReservations.stream()
                .map(reservation -> reservation.getSlot().getId())
                .collect(Collectors.toSet());
        return slotRepository.findAvailableSlots(reservedSlotIds, vehicleType, pageable);
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
}