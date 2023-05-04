package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Demande;
import com.groupe2.redline.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/demande")
public class DemandeController {
    @Autowired
    private DemandeService demandeService;

    @PostMapping("/creer")
    public ResponseEntity<Demande> addDemande(
            @RequestParam String nom,
            @RequestParam String email,
            @RequestParam int duree,
            @RequestParam Date proposition1,
            @RequestParam Date proposition2,
            @RequestParam Date proposition3,
            @RequestParam String description
    ) {

        Demande nouvelleDemande = demandeService.creer(nom, email, duree, proposition1, proposition2, proposition3, description);

        return ResponseEntity.ok(nouvelleDemande);
    }
}
