package com.groupe2.redline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UtilisateurDto {
    @NotBlank
    private String nom;

    @Email
    @NotBlank
    private String mail;

    @NotBlank
    private String motDePasse;

    @NotNull
    private Long idRole;

    public String getNom() {
        return nom;
    }

    public UtilisateurDto setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public UtilisateurDto setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public UtilisateurDto setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
        return this;
    }

    public Long getIdRole() {
        return idRole;
    }

    public UtilisateurDto setIdRole(Long idRole) {
        this.idRole = idRole;
        return this;
    }
}
