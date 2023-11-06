package org.vehicule.wsvehicule.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Vehicule>> readAll(@RequestHeader Map<String,String> headers) {
        if (tokenResponseService.validateAuthorization(headers))
            return new ResponseEntity<>(vehiculeService.readAll(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public Vehicule createVehicule(@RequestHeader Map<String,String> headers, @RequestBody Vehicule kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            return vehiculeService.create(kilometrage);
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
    public ResponseEntity<Vehicule> updateVehicule(@RequestHeader Map<String,String> headers, @PathVariable Long id, @RequestBody Vehicule kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            return new ResponseEntity<>(vehiculeService.update(id, kilometrage), HttpStatus.OK) ;
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicule(@RequestHeader Map<String,String> headers, @PathVariable Integer id) {
        if (tokenResponseService.validateAuthorization(headers)) {
            vehiculeService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

}
