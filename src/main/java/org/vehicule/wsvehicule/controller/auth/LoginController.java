package org.vehicule.wsvehicule.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.auth.TokenResponse;
import org.vehicule.wsvehicule.model.auth.Utilisateur;
import org.vehicule.wsvehicule.service.TokenResponseService;
import org.vehicule.wsvehicule.service.UtilisateurService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.vehicule.wsvehicule.util.TokenGenerator.getJWTToken;


@RestController
@RequestMapping("/login")
public class LoginController {
    private final UtilisateurService utilisateurService;
    private final TokenResponseService tokenResponseService;

    @Autowired
    public LoginController(UtilisateurService utilisateurService, TokenResponseService tokenResponseService) {
        this.utilisateurService = utilisateurService;
        this.tokenResponseService = tokenResponseService;
    }

    @GetMapping("/print-all-headers")
    public Map<String,String> getAllheaders(@RequestHeader Map<String,String> headers){
        System.out.println("============== \n\n"+headers.get("authorization")+"\n\n==============");

        return headers;
    }

    @GetMapping("test-token/{token}")
    public Boolean test(@PathVariable String token) {
        return tokenResponseService.isValidToken(token);
    }

    @GetMapping
    public List<Utilisateur> readAll(@RequestHeader Map<String,String> headers) {
        if (tokenResponseService.validateAuthorization(headers))
            return utilisateurService.readAll();
        else
            throw new RuntimeException("- Access Denied -");
    }

    @PostMapping
    public TokenResponse Login(@RequestBody Utilisateur utilisateur) throws NoSuchAlgorithmException, InvalidKeyException {
        Optional<TokenResponse> tr;

        if (utilisateurService.isRegisered(utilisateur) != null) {
            utilisateur = utilisateurService.isRegisered(utilisateur);
            tr = tokenResponseService.getValidToken(utilisateur);
            if (tr.isPresent()) {
                return tr.get();
            }
            else
                return tokenResponseService.insertToken(utilisateur, getJWTToken(utilisateur.getEmail()));
        }
        return new TokenResponse(utilisateur, "Utilisateur absent de la base de données");
    }



}
