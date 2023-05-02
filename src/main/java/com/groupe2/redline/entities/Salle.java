package com.groupe2.redline.entities;
import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "salle")
public class Salle implements Serializable {

        @Id
        @GeneratedValue
        @Column(name = "id")
        private Long id;
        @Column(name = "libelle")
        private String libelle;
        @Column(name = "adresse")
        private String adresse;
        @Column(name = "description")
        private String description;
        @Column(name = "actif")
        private boolean actif;
}
