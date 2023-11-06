package org.vehicule.wsvehicule.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.business.Kilometrage;
import org.vehicule.wsvehicule.service.KilometrageService;
import org.vehicule.wsvehicule.service.TokenResponseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/kilometrages")
public class KilometrageController {
    private final KilometrageService kilometrageService;
    private final TokenResponseService tokenResponseService;

    @Autowired
    public KilometrageController(KilometrageService kilometrageService, TokenResponseService tokenResponseService) {
        this.kilometrageService = kilometrageService;
        this.tokenResponseService = tokenResponseService;
    }

    @GetMapping
    public ResponseEntity<List<Kilometrage>> readAll(@RequestHeader Map<String,String> headers) {
        if (tokenResponseService.validateAuthorization(headers))
            return new ResponseEntity<>(kilometrageService.readAll(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<Kilometrage> createKilometrage(@RequestHeader Map<String,String> headers, @RequestBody Kilometrage kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            return new ResponseEntity<>(kilometrageService.create(kilometrage), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Kilometrage>> readKilometrage(@RequestHeader Map<String,String> headers, @PathVariable Long id) {
        if (tokenResponseService.validateAuthorization(headers))
            return new ResponseEntity<>(kilometrageService.read(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kilometrage> updateKilometrage(@RequestHeader Map<String,String> headers, @PathVariable Long id, @RequestBody Kilometrage kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
           return new ResponseEntity<>(kilometrageService.update(id, kilometrage), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKilometrage(@RequestHeader Map<String,String> headers, @PathVariable Integer id) {
        if (tokenResponseService.validateAuthorization(headers)) {
            kilometrageService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

}
