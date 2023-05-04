package com.groupe2.redline.controllers.dto;

import com.groupe2.redline.entities.Salle;

import java.util.List;

public class SiteDTO {

    private String libelle;

    private String description;

    private String adresse;


    public SiteDTO() {

    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
