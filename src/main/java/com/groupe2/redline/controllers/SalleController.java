package com.groupe2.redline.controllers;

import com.groupe2.redline.dto.SalleDto;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.services.SalleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.groupe2.redline.exceptions.CreneauIndisponibleException;

import com.groupe2.redline.exceptions.SalleInactiveException;
import com.groupe2.redline.exceptions.SiteInactifException;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salle")

public class SalleController {


    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Salle>> getSalles() {
        List<Salle> salles = this.salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

    @PostMapping("/add")
    public ResponseEntity <Salle> addSalle(@RequestBody SalleDto salleDto) {
        try {
            return ResponseEntity.status(201).body(salleService.addSalle(salleDto));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/get/{id}")
    public String getSalleById(@PathVariable Long id) {
        return "le fichier a bien été obtenu";
    }

    @Operation(summary = "Modifier un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site modifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Salle> editSalle(@PathVariable Long id, @RequestBody SalleDto salleDto) throws EntityNotFoundException {
        return new ResponseEntity<>(salleService.editSalle(id, salleDto), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteSalleById(@PathVariable Long id) {
        return "le fichier a bien été supprimé";
    }

    @PostMapping("/get/{id}/reserver")
    public ResponseEntity<String> reserver(@PathVariable Long id, @RequestParam Date date, @RequestParam int creneau, @RequestParam Long idAuteur) {
        // TODO Associer à une demande (argument optionnel)
        // TODO Récupérer automatiquement l'utilisateur connecté (nécessite d'implémenter l'authentification)

        try {
            salleService.reserver(id, date, creneau, idAuteur);
            return ResponseEntity.status(201).body("Réservation enregistrée.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (SalleInactiveException e) {
            return ResponseEntity.status(409).body("La salle spécifiée est inactive.");
        } catch (SiteInactifException e) {
            return ResponseEntity.status(409).body("Le site dans lequel est situé la salle spécifiée est inactif.");
        } catch (CreneauIndisponibleException e) {
            return ResponseEntity.status(409).body("Une réservation existe déjà sur ce créneau.");
        }
    }

    @PatchMapping("/get/{id}/activer")
    public ResponseEntity<String> activer(@PathVariable Long id) {
        Optional<Salle> salle = salleService.findById(id);

        if (salle.isPresent()) {
            if (salleService.setActif(salle.get(), true)) {
                return ResponseEntity.status(200).body("La salle a été activée.");
            }
            return ResponseEntity.status(409).body("La salle est déjà active.");
        }
        return ResponseEntity.status(404).body("La salle spécifiée n'existe pas.");
    }

    @PatchMapping("/get/{id}/desactiver")
    public ResponseEntity<String> desactiver(@PathVariable Long id) {
        Optional<Salle> salle = salleService.findById(id);

        if (salle.isPresent()) {
            if (salleService.setActif(salle.get(), false)) {
                return ResponseEntity.status(200).body("La salle a été désactivée.");
            }
            return ResponseEntity.status(409).body("La salle est déjà inactive.");
        }
        return ResponseEntity.status(404).body("La salle spécifiée n'existe pas.");
    }
}