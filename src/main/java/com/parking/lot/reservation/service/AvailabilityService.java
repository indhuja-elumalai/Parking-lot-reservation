package com.parking.lot.reservation.service;

import com.parking.lot.reservation.entity.Reservation;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.enums.VehicleType;
import com.parking.lot.reservation.repository.ReservationRepository;
import com.parking.lot.reservation.repository.SlotRepository;
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

    public List<Slot> findAllAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        List<Reservation> conflictingReservations = reservationRepository.findAllConflictingReservations(startTime, endTime);

        Set<Long> reservedSlotIds = conflictingReservations.stream()
                .map(reservation -> reservation.getSlot().getId())
                .collect(Collectors.toSet());

        return slotRepository.findAll().stream()
                .filter(slot -> !reservedSlotIds.contains(slot.getId()))
                .collect(Collectors.toList());
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