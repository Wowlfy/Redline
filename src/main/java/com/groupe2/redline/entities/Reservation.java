package com.groupe2.redline.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "creneau")
    private int creneau;

    @Column(name = "valide")
    private boolean valide;
    @ManyToOne
    @JoinColumn(name = "id_salle")
    private Salle salle;

    @JoinColumn(name = "id_utilisateur")

    @OneToOne
    @JoinColumn(name="id_demande", referencedColumnName="id", nullable=true)
    private Demande demande;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}
