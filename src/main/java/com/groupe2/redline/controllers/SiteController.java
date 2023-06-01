package com.groupe2.redline.controllers;

import com.groupe2.redline.dto.SiteDto;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.exceptions.SiteDejaActifException;
import com.groupe2.redline.exceptions.SiteDejaInactifException;
import com.groupe2.redline.services.SiteService;
import com.groupe2.redline.validation.groups.Creation;
import com.groupe2.redline.validation.groups.Modification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/site")
@Validated
@SecurityRequirement(name = "Authorization")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"EXTERNE", "ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Site>> getSites() {
        return ResponseEntity.status(HttpStatus.OK).body(siteService.getAllSites());
    }

    @Operation(summary = "Ajouter un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Site créé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @Validated(Creation.class)
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Site> addSite(@RequestBody @Valid SiteDto siteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.addSite(siteDto));
    }

    @Operation(summary = "Modifier un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site modifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping(value = "/get/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated(Modification.class)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Site> updateSite(@PathVariable Long id, @RequestBody @Valid SiteDto siteDTO) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(siteService.editSite(id, siteDTO));
    }

    @PatchMapping("/get/{id}/activer")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Site> activer(@PathVariable Long id) throws SiteDejaActifException {
        return ResponseEntity.status(HttpStatus.OK).body(siteService.activer(id));
    }

    @PatchMapping("/get/{id}/desactiver")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Site> desactiver(@PathVariable Long id) throws SiteDejaInactifException {
        return ResponseEntity.status(HttpStatus.OK).body(siteService.desactiver(id));
    }
}
