package org.vehicule.wsvehicule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.vehicule.wsvehicule.model.Kilometrage;

import java.util.List;
import java.util.Optional;

@Repository
public interface KilometrageRepository extends JpaRepository<Kilometrage, Long> {
    Optional<Kilometrage> findById(Long id);
    @Query("select k from Kilometrage k order by k.id")
    List<Kilometrage> findAll();
}
