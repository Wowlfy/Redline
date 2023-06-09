package com.groupe2.redline.repositories;

import com.groupe2.redline.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

    // CAST(: as date) pour permettre la comparaison avec une date ne stockant que jour/mois/année
    /** Récupérer les salles n'ayant pas de réservation sur cette date + créneau. */
    @Query("SELECT s FROM Salle s WHERE s.site.actif = true AND s.actif = true AND s.id NOT IN (SELECT r.salle.id FROM Reservation r WHERE r.date = CAST(:dateRecherchee AS date) AND r.creneau = :creneauRecherche)")
    List<Salle> rechercherSallesDisponibles(@Param("dateRecherchee") Date dateRecherchee, @Param("creneauRecherche") int creneauRecherche);
}
