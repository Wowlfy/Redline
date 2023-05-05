package com.groupe2.redline.dto;

import java.util.Date;

public class DemandeDto {

    private String nomDemander;

    private String email;

    private int duree;

    private Date propositionDate1;

    private Date propositionDate2;

    private Date propositionDate3;

    private String description;

    public DemandeDto() {

    }

    public String getNomDemander() {
        return nomDemander;
    }

    public void setNomDemander(String nomDemander) {
        this.nomDemander = nomDemander;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getPropositionDate1() {
        return propositionDate1;
    }

    public void setPropositionDate1(Date propositionDate1) {
        this.propositionDate1 = propositionDate1;
    }

    public Date getPropositionDate2() {
        return propositionDate2;
    }

    public void setPropositionDate2(Date propositionDate2) {
        this.propositionDate2 = propositionDate2;
    }

    public Date getPropositionDate3() {
        return propositionDate3;
    }

    public void setPropositionDate3(Date propositionDate3) {
        this.propositionDate3 = propositionDate3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
