package org.vehicule.wsvehicule.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.business.Vehicule;
import org.vehicule.wsvehicule.model.business.Vehicule;
import org.vehicule.wsvehicule.service.TokenResponseService;
import org.vehicule.wsvehicule.service.VehiculeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {
    private final VehiculeService vehiculeService;
    private final TokenResponseService tokenResponseService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService, TokenResponseService tokenResponseService) {
        this.vehiculeService = vehiculeService;
        this.tokenResponseService = tokenResponseService;
    }

    @GetMapping
    public List<Vehicule> readAll(@RequestHeader Map<String,String> headers) {
        if (tokenResponseService.validateAuthorization(headers))
            return vehiculeService.readAll();
        else
            throw new RuntimeException("- Access Denied -");
    }

    @PostMapping
    public void createVehicule(@RequestHeader Map<String,String> headers, @RequestBody Vehicule kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            vehiculeService.create(kilometrage);
        else
            throw new RuntimeException("- Access Denied -");
    }

    @GetMapping("/{id}")
    public Optional<Vehicule> readVehicule(@RequestHeader Map<String,String> headers, @PathVariable Long id) {
        if (tokenResponseService.validateAuthorization(headers))
            return Optional.ofNullable(vehiculeService.read(id));
        else
            throw new RuntimeException("- Access Denied -");
    }

    @PutMapping("/{id}")
    public void updateVehicule(@RequestHeader Map<String,String> headers, @PathVariable Long id, @RequestBody Vehicule kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            vehiculeService.update(id, kilometrage);
        else
            throw new RuntimeException("- Access Denied -");
    }

    @DeleteMapping("/{id}")
    public void deleteVehicule(@RequestHeader Map<String,String> headers, @PathVariable Integer id) {
        if (tokenResponseService.validateAuthorization(headers))
            vehiculeService.delete(id);
        else
            throw new RuntimeException("- Access Denied -");
    }

}
