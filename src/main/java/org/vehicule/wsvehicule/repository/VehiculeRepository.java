package org.vehicule.wsvehicule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vehicule.wsvehicule.model.business.Vehicule;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
    Vehicule findById(Long id);
    @Query("select k from Kilometrage k order by k.id")
    List<Vehicule> findAll();
}
