package org.vehicule.wsvehicule.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vehicule.wsvehicule.model.auth.Utilisateur;
import org.vehicule.wsvehicule.service.UtilisateurService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public LoginController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public Boolean Login(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.isRegisered(utilisateur);
    }
}
