package com.groupe2.redline.entities;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "demande")
public class Demande {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "nom_demandeur")
    private String nom_demandeur;
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
}
