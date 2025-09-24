package com.parking.lot.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parking.lot.reservation.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "slot", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"slot_number", "floor_id"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reservations"})
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_number")
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    @JsonIgnoreProperties({"slots"}) // Prevents serialization loop
    private Floor floor;

    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
}