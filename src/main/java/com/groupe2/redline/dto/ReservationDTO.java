package com.groupe2.redline.dto;

import com.groupe2.redline.validation.groups.Creation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class ReservationDTO {

    @NotBlank(groups = Creation.class)
    @Future
    private Date date;
    @NotBlank(groups = Creation.class)
    private int creneau;
    @NotBlank(groups = Creation.class)
    private Long utilisateurId;
    @NotBlank(groups = Creation.class)
    private Long salleId;

    public Long getSalleId() {
        return salleId;
    }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCreneau() {
        return creneau;
    }

    public void setCreneau(int creneau) {
        this.creneau = creneau;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
