package org.vehicule.wsvehicule.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Kilometrage> readAll(@RequestHeader Map<String,String> headers) {
        if (tokenResponseService.validateAuthorization(headers))
            return kilometrageService.readAll();
        else
            throw new RuntimeException("- Access Denied -");
    }

    @PostMapping
    public void createKilometrage(@RequestHeader Map<String,String> headers, @RequestBody Kilometrage kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            kilometrageService.create(kilometrage);
        else
            throw new RuntimeException("- Access Denied -");
    }

    @GetMapping("/{id}")
    public Optional<Kilometrage> readKilometrage(@RequestHeader Map<String,String> headers, @PathVariable Long id) {
        if (tokenResponseService.validateAuthorization(headers))
            return kilometrageService.read(id);
        else
            throw new RuntimeException("- Access Denied -");
    }

    @PutMapping("/{id}")
    public void updateKilometrage(@RequestHeader Map<String,String> headers, @PathVariable Long id, @RequestBody Kilometrage kilometrage) {
        if (tokenResponseService.validateAuthorization(headers))
            kilometrageService.update(id, kilometrage);
        else
            throw new RuntimeException("- Access Denied -");
    }

    @DeleteMapping("/{id}")
    public void deleteKilometrage(@RequestHeader Map<String,String> headers, @PathVariable Integer id) {
        if (tokenResponseService.validateAuthorization(headers))
            kilometrageService.delete(id);
        else
            throw new RuntimeException("- Access Denied -");
    }

}
