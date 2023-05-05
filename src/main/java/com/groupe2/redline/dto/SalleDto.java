package com.groupe2.redline.dto;

import com.groupe2.redline.validation.groups.Creation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SalleDto {
    @NotBlank(groups = Creation.class)
    @Size(max = 50)
    private String libelle;

    @NotBlank(groups = Creation.class)
    @Size(max = 255)
    private String description;
    @NotNull(groups = Creation.class)
    @Positive
    private Integer nbPlaces;

    @NotNull(groups = Creation.class)
    private Long siteId;


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

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}
