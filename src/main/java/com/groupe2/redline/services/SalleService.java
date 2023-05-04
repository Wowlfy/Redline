package com.groupe2.redline.services;

import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.repository.ReservationRepository;
import com.groupe2.redline.repository.SalleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalleService {

    private final SalleRepository salleRepository;

    private final ReservationRepository reservationRepository;

    public SalleService(SalleRepository salleRepository, ReservationRepository reservationRepository) {
        this.salleRepository = salleRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Salle> getAllSalles() {
        List<Salle> salles = this.salleRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
        return salles;
    }

    public Optional<Salle> findById(Long id) {
        return salleRepository.findById(id);
    }

    public String addSalle(Salle salle) {
        return null;
    }

    /**
     * Tente d'enregistrer une réservation
     * @param salle La salle concernée
     * @param date La date à laquelle réserver la salle
     * @param creneau Le créneau sur lequel réserver la salle
     * @param auteur L'auteur de la réservation
     * @return Si la réservation a été enregistrée.
     */
    public boolean reserver(Salle salle, Date date, int creneau, Utilisateur auteur) {
        // Vérifier qu'une réservation similaire n'existe pas déjà
        Reservation nouvelleReservation = new Reservation();
        nouvelleReservation.setSalle(salle);
        nouvelleReservation.setDate(date);
        nouvelleReservation.setCreneau(creneau);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<Reservation> example = Example.of(nouvelleReservation, exampleMatcher);
        boolean creneauDisponible = !reservationRepository.exists(example);

        if (creneauDisponible) {
            nouvelleReservation.setUtilisateur(auteur);
            reservationRepository.saveAndFlush(nouvelleReservation);
            // Vrai : La réservation a bien été enregistrée
            return true;
        } else {
            // Faux : La réservation n'a pas été enregistrée, car une autre réservation existe déjà sur ce créneau
            return false;
        }
    }

    public Salle editSalle(Long id, Salle salle) {
        Salle existingSalle = salleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salle not found with id " + id));

        existingSalle.setLibelle(salle.getLibelle());
        existingSalle.setDescription(salle.getDescription());
        existingSalle.setNbPlaces(salle.getNbPlaces());

        return salleRepository.save(existingSalle);
    }
}
