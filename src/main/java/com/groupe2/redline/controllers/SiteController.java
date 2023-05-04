package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Site;
import com.groupe2.redline.exceptions.SiteDejaActifException;
import com.groupe2.redline.exceptions.SiteDejaInactifException;
import com.groupe2.redline.services.SiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/site")
public class SiteController {

    private SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Site>> getSites() {
        List<Site> sites = this.siteService.getAllSites();
        return ResponseEntity.ok(sites);
    }

    @Operation(summary = "Ajouter un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site créé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Site> addSite(@RequestBody Site site) {
        Site saveSite = siteService.addSite(site);
        return new ResponseEntity<>(saveSite, HttpStatus.OK);
    }

    @Operation(summary = "Modifier un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site modifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping(value = "/get/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Site> updateSite(@PathVariable Long id, @RequestBody Site site) throws EntityNotFoundException {
        return new ResponseEntity<>(siteService.editSite(id, site), HttpStatus.OK);
    }

    @PatchMapping("/get/{id}/activer")
    public ResponseEntity<String> activer(@PathVariable Long id) {
        try {
            siteService.activer(id);
            return ResponseEntity.status(200).body("Le site a été activé.");
        } catch (SiteDejaActifException e) {
            return ResponseEntity.status(409).body("Le site est déjà actif.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Le site spécifié n'existe pas.");
        }
    }

    @PatchMapping("/get/{id}/desactiver")
    public ResponseEntity<String> desactiver(@PathVariable Long id) {
        try {
            siteService.desactiver(id);
            return ResponseEntity.status(200).body("Le site a été désactivé.");
        } catch (SiteDejaInactifException e) {
            return ResponseEntity.status(409).body("Le site est déjà inactif.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Le site spécifié n'existe pas.");
        }
    }
}
