package org.vehicule.wsvehicule.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.auth.Utilisateur;
import org.vehicule.wsvehicule.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public LoginController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> readAll() {
        return utilisateurService.readAll();
    }

    @PostMapping
    public Boolean Login(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.isRegisered(utilisateur);
    }
}
