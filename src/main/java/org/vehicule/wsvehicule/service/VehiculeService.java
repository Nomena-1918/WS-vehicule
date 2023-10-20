package org.vehicule.wsvehicule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vehicule.wsvehicule.model.business.Vehicule;
import org.vehicule.wsvehicule.repository.VehiculeRepository;

import java.util.List;

@Service
public class VehiculeService {
    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public List<Vehicule> readAll() {
        return vehiculeRepository.findAll();
    }

    public void create(Vehicule vehicule) {
        vehiculeRepository.save(vehicule);
    }

    public Vehicule read(Long id) {
        return vehiculeRepository.findById(id);
    }

    public void update(Long id, Vehicule vehicule) {
        if (vehicule==null)
            return;

        Vehicule precedent  = vehiculeRepository.findById(id);
        precedent.setNom_modele(vehicule.getNom_modele());
        vehiculeRepository.save(precedent);
    }

    public void delete(Integer id) {
        vehiculeRepository.deleteById(id);
    }
}


