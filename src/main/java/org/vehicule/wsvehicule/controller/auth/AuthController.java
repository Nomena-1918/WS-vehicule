package org.vehicule.wsvehicule.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vehicule.wsvehicule.model.auth.TokenResponse;
import org.vehicule.wsvehicule.model.auth.Utilisateur;
import org.vehicule.wsvehicule.service.TokenResponseService;
import org.vehicule.wsvehicule.service.UtilisateurService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.vehicule.wsvehicule.util.TokenGenerator.getToken;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UtilisateurService utilisateurService;
    private final TokenResponseService tokenResponseService;

    @Autowired
    public AuthController(UtilisateurService utilisateurService, TokenResponseService tokenResponseService) {
        this.utilisateurService = utilisateurService;
        this.tokenResponseService = tokenResponseService;
    }


    @GetMapping
    public ResponseEntity<List<Utilisateur>> readAll(@RequestHeader Map<String,String> headers) {
        if (tokenResponseService.validateAuthorization(headers))
            return new ResponseEntity<>(utilisateurService.readAll(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> Login(@RequestBody Utilisateur utilisateur) throws NoSuchAlgorithmException, InvalidKeyException {
        Optional<TokenResponse> tr;

        if (utilisateurService.isRegisered(utilisateur) != null) {
            utilisateur = utilisateurService.isRegisered(utilisateur);
            tr = tokenResponseService.getValidToken(utilisateur);
            if (tr.isPresent()) {
                return new ResponseEntity<>(tr.get(), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(tokenResponseService.insertToken(utilisateur, getToken(utilisateur.getEmail())), HttpStatus.OK);
        }
        return new ResponseEntity<>( new TokenResponse(utilisateur, "Utilisateur absent de la base de données"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> Logout(@RequestHeader Map<String,String> headers) {
        try {
            if (tokenResponseService.validateAuthorization(headers)) {
                String token = headers.get("authorization");
                token = token.replace("Bearer ", "");
                tokenResponseService.expireToken(token);
                return new ResponseEntity<>("Logged out !", HttpStatus.OK);
            } else
                return new ResponseEntity<>("Token expiré", HttpStatus.UNAUTHORIZED);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }







    @GetMapping("/print-all-headers")
    public Map<String,String> getAllheaders(@RequestHeader Map<String,String> headers){
        System.out.println("============== \n\n"+headers.get("authorization")+"\n\n==============");

        return headers;
    }

    @GetMapping("/test-token/{token}")
    public Boolean test(@PathVariable String token) {
        return tokenResponseService.isValidToken(token);
    }



}
