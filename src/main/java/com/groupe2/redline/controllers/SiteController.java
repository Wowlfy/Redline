package com.groupe2.redline.controllers;

import com.groupe2.redline.controllers.dto.SiteDTO;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.services.SiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @GetMapping(value ="/get", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity <Site> addSite(@RequestBody Site site) {
        Site saveSite = siteService.addSite(site);
        return new ResponseEntity<>(saveSite, HttpStatus.OK);
    }

    @Operation(summary = "Modifier un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site modifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PatchMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity <Site> updateSite(@PathVariable Long id, @RequestBody SiteDTO siteDTO) throws EntityNotFoundException {
        return new ResponseEntity<>(siteService.editSite(id, siteDTO), HttpStatus.OK);
    }
}
