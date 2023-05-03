package com.groupe2.redline.entities;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "salle")
public class Salle implements Serializable {

        @Id
        @GeneratedValue
        private Long id;
        @Column(name = "libelle")
        private String libelle;
        @Column(name = "adresse")
        private String adresse;
        @Column(name = "description")
        private String description;

        @Column(name = "nb_places")
        private int nbPlaces;

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

        @Column(name = "actif")
        private boolean actif;

        @ManyToOne
        @JoinColumn(name = "id_site")
        private Site site;
        @OneToMany(mappedBy = "salle")
        private Set<Reservation> reservations;


        public Long getId() {
                return id;
        }

        public String getLibelle() {
                return libelle;
        }

        public void setLibelle(String libelle) {
                this.libelle = libelle;
        }

        public String getAdresse() {
                return adresse;
        }

        public void setAdresse(String adresse) {
                this.adresse = adresse;
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
