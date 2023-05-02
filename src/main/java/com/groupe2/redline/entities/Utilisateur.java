package com.groupe2.redline.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "mot_de_passe")
    private String mot_de_passe;
}
