package com.groupe2.redline.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "salle")
public class Salle {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "description")
    private String description;

    @Column(name = "nb_places")
    private int nbPlaces;

    @Column(name = "actif")
    private boolean actif;

    @ManyToOne
    @JoinColumn(name = "id_site")
    @JsonBackReference("site-salle")
    private Site site;


    @OneToMany(mappedBy = "salle")
    @JsonManagedReference("salle-reservation")
    private Set<Reservation> reservations;

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }


    public Long getId() {
        return id;
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
