package com.groupe2.redline.entities;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "demande")
public class Demande {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nom_demandeur")
    private String nomDemandeur;
    @Column(name = "email")
    private String email;
    @Column(name = "duree")
    private int duree;
    @Column(name = "proposition_date_1")
    private Date propositionDate1;
    @Column(name = "proposition_date_2")
    private Date propositionDate2;
    @Column(name = "proposition_date_3")
    private Date propositionDate3;
    @Column(name = "description")
    private String description;
    @Column(name = "traitee")
    private Boolean traitee;
    @OneToOne(mappedBy = "demande")
    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public String getNomDemandeur() {
        return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
        this.nomDemandeur = nomDemandeur;
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

    public Boolean getTraitee() {
        return traitee;
    }

    public void setTraitee(Boolean traitee) {
        this.traitee = traitee;
    }
}
