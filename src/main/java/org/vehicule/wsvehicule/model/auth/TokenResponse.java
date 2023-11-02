package org.vehicule.wsvehicule.model.auth;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "token_response")
public class TokenResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "date_expiration", nullable = false)
    private Timestamp date_expiration;

    public TokenResponse(Utilisateur utilisateur, String token) {
        utilisateur.setMdp(null);
        utilisateur.setId(null);
        this.utilisateur = utilisateur;
        this.token = token;
    }

    public TokenResponse() {
    }

}
