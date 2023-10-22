package org.vehicule.wsvehicule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vehicule.wsvehicule.model.auth.TokenResponse;
import org.vehicule.wsvehicule.model.auth.Utilisateur;
import org.vehicule.wsvehicule.repository.TokenResponseRepository;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Service
public class TokenResponseService {
    private final TokenResponseRepository tokenResponseRepository;

    @Value("${custom.token.duration}")
    private int tokenDuration;

    @Autowired
    public TokenResponseService(TokenResponseRepository tokenResponseRepository) {
        this.tokenResponseRepository = tokenResponseRepository;
    }

    public Boolean isValidToken(String token) {
        return tokenResponseRepository.findUtilisateurByValidToken(token.replace("Bearer ", "")).isPresent();
    }

    public Optional<TokenResponse> getValidToken(Utilisateur utilisateur) {
        return tokenResponseRepository.findValidTokenByUtilisateur(utilisateur);
    }

    public TokenResponse insertToken(Utilisateur utilisateur, String token) {
        TokenResponse tr = new TokenResponse(utilisateur, token);
        Timestamp t = new Timestamp(System.currentTimeMillis()+ (long) tokenDuration *60*1000);
        tr.setDate_expiration(t);

        return tokenResponseRepository.save(tr);
    }

    public Boolean validateAuthorization(Map<String,String> headers) {
        String token = headers.get("authorization");
        return token != null && isValidToken(token);
    }

}
