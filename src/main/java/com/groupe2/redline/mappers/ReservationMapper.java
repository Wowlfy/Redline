package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.ReservationDTO;
import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.exceptions.CreneauIndisponibleException;
import com.groupe2.redline.repositories.ReservationRepository;
import com.groupe2.redline.repositories.SalleRepository;
import com.groupe2.redline.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservationMapper {

    private final UtilisateurRepository utilisateurRepository;
    private final SalleRepository salleRepository;

    private final ReservationRepository reservationRepository;

    public ReservationMapper(UtilisateurRepository utilisateurRepository, SalleRepository salleRepository, ReservationRepository reservationRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.salleRepository = salleRepository;
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservationFromDto(ReservationDTO reservationDTO) throws CreneauIndisponibleException {

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(reservationDTO.getUtilisateurId());
        if (utilisateurOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Optional<Salle> salleOptional = salleRepository.findById(reservationDTO.getSalleId());
        if (salleOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Reservation newReservation = new Reservation();
        newReservation.setDate(reservationDTO.getDate());
        newReservation.setCreneau(reservationDTO.getCreneau());
        newReservation.setUtilisateur(utilisateurOptional.get());
        newReservation.setSalle(salleOptional.get());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<Reservation> example = Example.of(newReservation, exampleMatcher);
        if (reservationRepository.exists(example)) {
            throw new CreneauIndisponibleException();
        }
        return newReservation;
    }
}
