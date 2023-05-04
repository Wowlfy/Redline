package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.exceptions.CreneauIndisponibleException;
import com.groupe2.redline.exceptions.SalleInactiveException;
import com.groupe2.redline.services.SalleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salle")

public class SalleController {
    @Autowired
    private SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Salle>> getSalles() {
        List<Salle> salles = this.salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

    @PostMapping("/add")
    public String addSalle(@RequestBody Salle salle) {
        return salleService.addSalle(salle);

    }

    @GetMapping("/get/{id}")
    public String getSalleById(@PathVariable Long id) {
        return "le fichier a bien été obtenu";
    }

    @PutMapping("/update/{id}")
    public String updateSalleById(@PathVariable Long id) {
        return "le fichier a bien été modifié";
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