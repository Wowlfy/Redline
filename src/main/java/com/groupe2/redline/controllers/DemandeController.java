package com.groupe2.redline.controllers;

import com.groupe2.redline.dto.DemandeDto;
import com.groupe2.redline.entities.Demande;
import com.groupe2.redline.services.DemandeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demande")
public class DemandeController {

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping(value = "/creer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demande> addDemande(@RequestBody @Valid DemandeDto demandeDto) {

        Demande nouvelleDemande = demandeService.creer(demandeDto);

        return new ResponseEntity<>(nouvelleDemande, HttpStatus.OK);
    }
}
