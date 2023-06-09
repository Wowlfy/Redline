package com.groupe2.redline.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "creneau")
    private int creneau;


    @ManyToOne
    @JoinColumn(name = "id_salle")
    @JsonBackReference("salle-reservation")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    @JsonBackReference("utilisateur-reservations")
    private Utilisateur utilisateur;

    @OneToOne
    @JoinColumn(name="id_demande", referencedColumnName="id", nullable=true)
    private Demande demande;


    public Long getId() {
        return id;
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

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
}
