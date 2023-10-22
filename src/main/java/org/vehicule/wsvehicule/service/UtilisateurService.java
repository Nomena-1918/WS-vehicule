package org.vehicule.wsvehicule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.vehicule.wsvehicule.model.auth.Utilisateur;
import org.vehicule.wsvehicule.model.business.Vehicule;
import org.vehicule.wsvehicule.repository.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    public Utilisateur isRegisered(Utilisateur utilisateur) {
        return utilisateurRepository.findUtilisateurByEmailAndMdp(utilisateur.getEmail(), utilisateur.getMdp()).get();
    }

    public List<Utilisateur> readAll() {
        return utilisateurRepository.findAll();
    }

}
