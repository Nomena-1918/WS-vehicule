package org.vehicule.wsvehicule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vehicule.wsvehicule.model.business.Kilometrage;
import org.vehicule.wsvehicule.model.business.Vehicule;
import org.vehicule.wsvehicule.repository.KilometrageRepository;
import org.vehicule.wsvehicule.repository.VehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KilometrageService {
    private final KilometrageRepository kilometrageRepository;
    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public KilometrageService(KilometrageRepository kilometrageRepository, VehiculeRepository vehiculeRepository) {
        this.kilometrageRepository = kilometrageRepository;
        this.vehiculeRepository = vehiculeRepository;
    }


    public Kilometrage create(Kilometrage kilometrage) {
        Vehicule vehicule = kilometrage.getVehicule();
        if(vehicule != null && (vehicule.getId() == null ||
                vehiculeRepository.findById(vehicule.getId()) == null)) {
            vehicule = vehiculeRepository.save(vehicule);
        }
        kilometrage.setVehicule(vehicule);
        return kilometrageRepository.save(kilometrage);
    }


    public Optional<Kilometrage> read(Long id) {
        return kilometrageRepository.findById(id);
    }

    public List<Kilometrage> readAll() {
        return kilometrageRepository.findAll();
    }

    public Kilometrage update(Long id, Kilometrage kilometrage) {
        if (kilometrage == null)
            return null;

        Optional<Kilometrage> data  = kilometrageRepository.findById(id);

        if (data.isEmpty())
            return null;

        Kilometrage precedent = data.get();

        if (kilometrage.getVehicule() != null)
            precedent.setVehicule(kilometrage.getVehicule());

        if (kilometrage.getDebutKm() != null)
            precedent.setDebutKm(kilometrage.getDebutKm());

        if (kilometrage.getFinKm() != null)
            precedent.setFinKm(kilometrage.getFinKm());

        if (kilometrage.getDate_trajet() != null)
            precedent.setDate_trajet(kilometrage.getDate_trajet());

        return kilometrageRepository.save(precedent);
    }

    public void delete(Integer id) {
        kilometrageRepository.deleteById(Long.valueOf(id));
    }

}
