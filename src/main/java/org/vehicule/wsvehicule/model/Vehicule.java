package org.vehicule.wsvehicule.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicule")
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom_modele", nullable = false)
    private String nom_modele;

}
