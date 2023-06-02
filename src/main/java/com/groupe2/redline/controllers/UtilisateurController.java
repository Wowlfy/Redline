package com.groupe2.redline.controllers;

import com.groupe2.redline.dto.UtilisateurDto;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.exceptions.UtilisateurDejaPresentException;
import com.groupe2.redline.services.UtilisateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilisateur")
@Validated
@SecurityRequirement(name = "Authorization")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/add")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Utilisateur> addUtilisateur(@RequestBody @Valid UtilisateurDto utilisateurDto) throws UtilisateurDejaPresentException {
            return ResponseEntity.ok(utilisateurService.addUtilisateur(utilisateurDto));
    }
}
