package org.vehicule.wsvehicule.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.business.Vehicule;
import org.vehicule.wsvehicule.service.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {
    VehiculeService vehiculeService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    public List<Vehicule> readAll() {
        return vehiculeService.readAll();
    }
    @PostMapping
    public void createVehicule(@RequestBody Vehicule vehicule) {
        vehiculeService.create(vehicule);
    }

    @GetMapping("/{id}")
    public Vehicule readVehicule(@PathVariable Long id) {
        return vehiculeService.read(id);
    }

    @PutMapping("/{id}")
    public void updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        vehiculeService.update(id, vehicule);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable Integer id) {
        vehiculeService.delete(id);
    }

}
