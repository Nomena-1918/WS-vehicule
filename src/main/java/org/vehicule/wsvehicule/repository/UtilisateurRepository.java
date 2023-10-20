package org.vehicule.wsvehicule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vehicule.wsvehicule.model.auth.Utilisateur;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findUtilisateurByEmailAndMdp(String email, String mdp);
}
