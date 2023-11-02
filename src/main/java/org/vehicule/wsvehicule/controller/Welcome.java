package org.vehicule.wsvehicule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
    @GetMapping({"/error", "/"})
    public String index() {
        return "Welcome to the Vehicule API !";
    }
}
