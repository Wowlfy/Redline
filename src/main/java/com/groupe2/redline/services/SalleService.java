package com.groupe2.redline.services;

import com.groupe2.redline.dto.ReservationDTO;
import com.groupe2.redline.dto.SalleDto;
import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.exceptions.CreneauIndisponibleException;
import com.groupe2.redline.exceptions.SalleInactiveException;
import com.groupe2.redline.exceptions.SiteInactifException;
import com.groupe2.redline.mappers.ReservationMapper;
import com.groupe2.redline.mappers.SalleMapper;
import com.groupe2.redline.repositories.ReservationRepository;
import com.groupe2.redline.repositories.SalleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalleService {

    private final SalleMapper salleMapper;


    private final SalleRepository salleRepository;


    private final ReservationRepository reservationRepository;


    private final UtilisateurService utilisateurService;

    private final ReservationMapper reservationMapper;

    public SalleService(SalleMapper salleMapper, SalleRepository salleRepository, ReservationRepository reservationRepository, UtilisateurService utilisateurService, ReservationMapper reservationMapper) {
        this.salleMapper = salleMapper;
        this.salleRepository = salleRepository;
        this.reservationRepository = reservationRepository;
        this.utilisateurService = utilisateurService;
        this.reservationMapper = reservationMapper;
    }

    public List<Salle> getAllSalles() {
        List<Salle> salles = this.salleRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
        return salles;
    }

    public Optional<Salle> findById(Long id) {
        return salleRepository.findById(id);
    }

    public Salle addSalle(SalleDto salleDto) throws EntityNotFoundException {
        Salle salle = salleMapper.createSalleFromDto(salleDto);

        Salle savedSalle = this.salleRepository.save(salle);
        return savedSalle;
    }

    /**
     * Tente d'enregistrer une réservation
     */
    public void reserver(ReservationDTO reservationDto) throws CreneauIndisponibleException, SalleInactiveException, EntityNotFoundException, SiteInactifException {
        Reservation updatedReservation = reservationMapper.createReservationFromDto(reservationDto);
        reservationRepository.saveAndFlush(updatedReservation);
    }

    public boolean setActif(Salle salle, boolean valeur) {
        if (salle.isActif() == valeur) {
            return false;
        }

        salle.setActif(valeur);
        salleRepository.saveAndFlush(salle);
        return true;
    }

    public Salle editSalle(Long id, SalleDto salleDto) {
        Optional<Salle> existingSalle = salleRepository.findById(id);
        if (existingSalle.isEmpty()) {
            throw new EntityNotFoundException("La salle avec l'ID " + id + " n'existe pas.");
        }

        Salle updatedSalle = salleMapper.editSalleFromDto(existingSalle.get(), salleDto);
        Salle savedSalle = salleRepository.save(updatedSalle);

        return savedSalle;
    }
}
