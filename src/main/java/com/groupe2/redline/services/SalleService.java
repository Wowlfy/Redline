package com.groupe2.redline.services;

import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.repository.ReservationRepository;
import com.groupe2.redline.repository.SalleRepository;
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

    public boolean reserver(Salle salle, Date date, int creneau, Utilisateur utilisateur) {
        Reservation nouvelleReservation = new Reservation();
        nouvelleReservation.setSalle(salle);
        nouvelleReservation.setDate(date);
        nouvelleReservation.setCreneau(creneau);
        nouvelleReservation.setUtilisateur(utilisateur);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<Reservation> example = Example.of(nouvelleReservation, exampleMatcher);

        boolean creneauDisponible = !reservationRepository.exists(example);

        if (creneauDisponible) {
            reservationRepository.saveAndFlush(nouvelleReservation);
            return true;
        } else {
            return false;
        }
    }
}
