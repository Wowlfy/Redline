package com.groupe2.redline.services;

import com.groupe2.redline.dto.ReservationDTO;
import com.groupe2.redline.dto.SalleDto;
import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.exceptions.*;
import com.groupe2.redline.mappers.ReservationMapper;
import com.groupe2.redline.mappers.SalleMapper;
import com.groupe2.redline.repositories.ReservationRepository;
import com.groupe2.redline.repositories.SalleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalleService {

    private final SalleMapper salleMapper;
    private final SalleRepository salleRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public SalleService(SalleMapper salleMapper, SalleRepository salleRepository, ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.salleMapper = salleMapper;
        this.salleRepository = salleRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<Salle> getAllSalles() {
        return this.salleRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
    }

    @Deprecated
    public Optional<Salle> findById(Long id) {
        return salleRepository.findById(id);
    }

    public Salle addSalle(SalleDto salleDto) throws EntityNotFoundException {
        Salle salle = salleMapper.createSalleFromDto(salleDto);

        return salleRepository.save(salle);
    }

    /**
     * Tente d'enregistrer une réservation
     *
     * @param salle  L'id de la salle concernée
     * @param reservationDto     DTO Contenant date + créneau
     * @param auteur  L'auteur de la réservation (injecté par spring)
     */
    public Reservation reserver(ReservationDTO reservationDto, Utilisateur auteur, Salle salle) throws CreneauIndisponibleException, SalleInactiveException, EntityNotFoundException, SiteInactifException {
        Reservation nouvelleReservation = reservationMapper.createReservationFromDto(reservationDto);
        if (!salle.isActif()) throw new SalleInactiveException();
        if (!salle.getSite().isActif()) throw new SiteInactifException();
        nouvelleReservation.setSalle(salle);
        nouvelleReservation.setUtilisateur(auteur);

        return reservationRepository.saveAndFlush(nouvelleReservation);
    }

    public Salle activer(Long idSalle) throws SalleDejaActiveException, EntityNotFoundException {
        Salle salle = findSalleOrThrow(idSalle);

        if (!salle.isActif()) throw new SalleDejaActiveException("La salle est déjà active.");

        salle.setActif(true);

        return salleRepository.saveAndFlush(salle);
    }

    public Salle desactiver(Long idSalle) throws SalleDejaInactiveException, EntityNotFoundException {
        Salle salle = findSalleOrThrow(idSalle);

        if (!salle.isActif()) throw new SalleDejaInactiveException("La salle est déjà inactive.");

        salle.setActif(false);

        return salleRepository.saveAndFlush(salle);
    }

    public Salle editSalle(Long id, SalleDto salleDto) throws EntityNotFoundException {
        Salle salle = findSalleOrThrow(id);

        return salleRepository.save(salleMapper.editSalleFromDto(salle, salleDto));
    }

    public List<SalleDto> rechercherSallesDisponibles(Date dateRecherchee, int creneauRecherche) {
        List<Salle> salles = salleRepository.rechercherSallesDisponibles(dateRecherchee, creneauRecherche);
        List<SalleDto> dtos = salles.stream().map(salleMapper::createDtoFromSalle).toList();
        return dtos;
    }

    /**
     * Récupérer une salle par son id, ou lancer une erreur.
     *
     * @param id L'id de la salle recherchée
     * @return La Salle
     * @throws EntityNotFoundException Si l'id ne correspond à aucune salle
     */
    private Salle findSalleOrThrow(Long id) throws EntityNotFoundException {
        Optional<Salle> salleOptional = salleRepository.findById(id);
        if (salleOptional.isEmpty()) throw new EntityNotFoundException("La salle spécifiée n'existe pas.");
        return salleOptional.get();
    }
}
