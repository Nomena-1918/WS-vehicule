package org.vehicule.wsvehicule.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.business.Kilometrage;
import org.vehicule.wsvehicule.service.KilometrageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kilometrages")
public class KilometrageController {
    KilometrageService kilometrageService;

    @Autowired
    public KilometrageController(KilometrageService kilometrageService) {
        this.kilometrageService = kilometrageService;
    }

    @GetMapping
    public List<Kilometrage> readAll() {
        return kilometrageService.readAll();
    }

    @PostMapping
    public void createKilometrage(@RequestBody Kilometrage kilometrage) {
        kilometrageService.create(kilometrage);
    }

    @GetMapping("/{id}")
    public Optional<Kilometrage> readKilometrage(@PathVariable Long id) {
        return kilometrageService.read(id);
    }

    @PutMapping("/{id}")
    public void updateKilometrage(@PathVariable Long id, @RequestBody Kilometrage kilometrage) {
        kilometrageService.update(id, kilometrage);
    }

    @DeleteMapping("/{id}")
    public void deleteKilometrage(@PathVariable Integer id) {
        kilometrageService.delete(id);
    }

}
