package com.parking.lot.reservation.repository;

import com.parking.lot.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.slot.id = :slotId AND (" +
            "(:startTime BETWEEN r.startTime AND r.endTime) OR " +
            "(:endTime BETWEEN r.startTime AND r.endTime) OR " +
            "(r.startTime BETWEEN :startTime AND :endTime) OR " +
            "(r.endTime BETWEEN :startTime AND :endTime))")
    List<Reservation> findConflictingReservationsBySlotId(@Param("slotId") Long slotId,
                                                          @Param("startTime") LocalDateTime startTime,
                                                          @Param("endTime") LocalDateTime endTime);

    @Query("SELECT r FROM Reservation r WHERE " +
            "(:startTime BETWEEN r.startTime AND r.endTime) OR " +
            "(:endTime BETWEEN r.startTime AND r.endTime) OR " +
            "(r.startTime BETWEEN :startTime AND :endTime) OR " +
            "(r.endTime BETWEEN :startTime AND :endTime)")
    List<Reservation> findOverlappingReservations(@Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    @Query("SELECT r FROM Reservation r WHERE " +
            "((r.startTime <= :endTime AND r.endTime >= :startTime))")
    List<Reservation> findAllConflictingReservations(@Param("startTime") LocalDateTime startTime,
                                                     @Param("endTime") LocalDateTime endTime);
    Optional<Reservation> findById(Long id);
}
