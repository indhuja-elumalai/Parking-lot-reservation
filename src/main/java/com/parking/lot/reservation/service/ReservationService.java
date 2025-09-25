package com.parking.lot.reservation.service;

import com.parking.lot.reservation.dto.ReservationRequestDto;
import com.parking.lot.reservation.entity.Reservation;
import com.parking.lot.reservation.entity.Slot;
import com.parking.lot.reservation.entity.VehicleRate;
import com.parking.lot.reservation.repository.ReservationRepository;
import com.parking.lot.reservation.repository.SlotRepository;
import com.parking.lot.reservation.repository.VehicleRateRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;
    private final VehicleRateRepository vehicleRateRepository;
    private final AvailabilityService availabilityService;

    public ReservationService(ReservationRepository reservationRepository, SlotRepository slotRepository,
                              VehicleRateRepository vehicleRateRepository, AvailabilityService availabilityService) {
        this.reservationRepository = reservationRepository;
        this.slotRepository = slotRepository;
        this.vehicleRateRepository = vehicleRateRepository;
        this.availabilityService = availabilityService;
    }

    public Reservation createReservation(ReservationRequestDto reservationDto) {
        
        // Validation: Ensure startTime is before endTime
        if (reservationDto.getStartTime().isAfter(reservationDto.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        
        // Fix: Ensure reservation does not exceed 24 hours using minutes for precision
        Duration duration = Duration.between(reservationDto.getStartTime(), reservationDto.getEndTime());
        if (duration.toMinutes() > 24 * 60) {
            throw new IllegalArgumentException("Reservation cannot exceed 24 hours.");
        }

        Slot slotToReserve;

        if (reservationDto.getSlotId() != null) {
            slotToReserve = slotRepository.findById(reservationDto.getSlotId())
                    .orElseThrow(() -> new IllegalArgumentException("Slot not found with ID: " + reservationDto.getSlotId()));
            
            if (reservationRepository.findConflictingReservation(
                    slotToReserve.getId(),
                    reservationDto.getStartTime(),
                    reservationDto.getEndTime()).isPresent()) {
                throw new IllegalStateException("The requested slot is already reserved for this time.");
            }
        } else {
            slotToReserve = availabilityService.findAvailableSlotForVehicle(
                    reservationDto.getStartTime(),
                    reservationDto.getEndTime(),
                    reservationDto.getVehicleType());

            if (slotToReserve == null) {
                throw new IllegalStateException("No available slots for the requested vehicle type and time.");
            }
        }

        if (slotToReserve.getVehicleType() != reservationDto.getVehicleType()) {
            throw new IllegalArgumentException("Vehicle type does not match the selected slot type.");
        }

        VehicleRate rate = vehicleRateRepository.findById(reservationDto.getVehicleType())
                .orElseThrow(() -> new IllegalStateException("Rate for vehicle type not found. Please contact an administrator."));
        
        Reservation reservation = new Reservation();
        reservation.setSlot(slotToReserve);
        reservation.setVehicleNumber(reservationDto.getVehicleNumber());
        reservation.setStartTime(reservationDto.getStartTime());
        reservation.setEndTime(reservationDto.getEndTime());

        long hours = (long) Math.ceil(duration.toMinutes() / 60.0);
        double totalCost = hours * rate.getHourlyRate();
        reservation.setTotalCost(totalCost);

        return reservationRepository.save(reservation);
    }
    
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
    
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}