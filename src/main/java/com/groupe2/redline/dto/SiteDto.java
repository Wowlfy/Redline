package com.groupe2.redline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SiteDto {
    @NotBlank
    @Size(max = 50)
    private String libelle;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 150)
    private String adresse;


    public SiteDto() {

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
