package com.groupe2.redline.controllers;

import com.groupe2.redline.dto.ReservationDTO;
import com.groupe2.redline.dto.SalleDto;
import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.exceptions.*;
import com.groupe2.redline.repositories.UtilisateurRepository;
import com.groupe2.redline.services.SalleService;
import com.groupe2.redline.validation.groups.Creation;
import com.groupe2.redline.validation.groups.Modification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/salle")
@Validated
@SecurityRequirement(name = "Authorization")
public class SalleController {


    private final SalleService salleService;
    private final UtilisateurRepository utilisateurRepository;

    public SalleController(SalleService salleService, UtilisateurRepository utilisateurRepository) {
        this.salleService = salleService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/get")
    @Secured({"EXTERNE", "ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Salle>> getSalles() {
        List<Salle> salles = this.salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

    @PostMapping("/add")
    @Validated(Creation.class)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Salle> addSalle(@RequestBody @Valid SalleDto salleDto) {
        try {
            return ResponseEntity.status(201).body(salleService.addSalle(salleDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(summary = "Modifier un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site modifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated(Modification.class)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Salle> editSalle(@PathVariable Long id, @RequestBody @Valid SalleDto salleDto) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(salleService.editSalle(id, salleDto));
    }

    @PostMapping("/get/{id}/reserver")
    @Validated(Creation.class)
    @Secured({"EXTERNE", "ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Reservation> reserver(@RequestBody @Valid ReservationDTO reservationDTO, @PathVariable("id") Salle salle, Principal principal) throws CreneauIndisponibleException, SalleInactiveException, SiteInactifException {
        // TODO Associer à une demande (argument optionnel)

        Utilisateur utilisateurConnecte = utilisateurRepository.findByMail(principal.getName()).orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(salleService.reserver(reservationDTO, utilisateurConnecte, salle));
    }

    @PatchMapping("/get/{id}/activer")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Salle> activer(@PathVariable Long id) throws SalleDejaActiveException {
        return ResponseEntity.status(HttpStatus.OK).body(salleService.activer(id));

    }

    @PatchMapping("/get/{id}/desactiver")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Salle> desactiver(@PathVariable Long id) throws SalleDejaInactiveException {
        return ResponseEntity.status(HttpStatus.OK).body(salleService.desactiver(id));
    }

    // TODO : Gestion des erreurs comme dans les autres controllers (controller advice)
    @GetMapping("/rechercher")
    @Secured({"EXTERNE", "ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<SalleDto>> rechercherSallesDisponibles(@Param("date") Date date, @Param("creneau") int creneau) {
        return ResponseEntity.ok(salleService.rechercherSallesDisponibles(date, creneau));
    }
}
