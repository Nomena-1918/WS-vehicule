package org.vehicule.wsvehicule.model.business;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Kilometrage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idVehicule", nullable = false)
    private Vehicule vehicule;

    @Column(name = "debutKm")
    private Double debutKm;

    @Column(name = "finKm")
    private Double finKm;

    @Column(name = "date_trajet")
    private Timestamp date_trajet;
}
