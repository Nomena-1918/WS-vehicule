package org.vehicule.wsvehicule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vehicule.wsvehicule.model.auth.TokenResponse;
import org.vehicule.wsvehicule.model.auth.Utilisateur;

import java.util.Optional;

@Repository
public interface TokenResponseRepository extends JpaRepository<TokenResponse, Long> {
    @Query("SELECT t FROM TokenResponse t WHERE t.utilisateur = :utilisateur AND t.date_expiration > CURRENT_TIMESTAMP")
    Optional<TokenResponse> findValidTokenByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);

    @Query("SELECT t FROM TokenResponse t WHERE t.token = :token AND t.date_expiration > CURRENT_TIMESTAMP")
    Optional<TokenResponse> findUtilisateurByValidToken(@Param("token") String token);

}