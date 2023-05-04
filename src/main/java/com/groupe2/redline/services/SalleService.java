package com.groupe2.redline.services;

import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.exceptions.CreneauIndisponibleException;
import com.groupe2.redline.exceptions.SalleInactiveException;
import com.groupe2.redline.exceptions.SiteInactifException;
import com.groupe2.redline.repository.ReservationRepository;
import com.groupe2.redline.repository.SalleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UtilisateurService utilisateurService;

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
     *
     * @param idSalle L'id de la salle concernée
     * @param date    La date à laquelle réserver la salle
     * @param creneau Le créneau sur lequel réserver la salle
     * @param idAuteur L'id de l'utilisateur effectuant la réservation
     */
    public void reserver(Long idSalle, Date date, int creneau, Long idAuteur) throws CreneauIndisponibleException, SalleInactiveException, EntityNotFoundException, SiteInactifException {
        // Récupérer les entités mentionnées dans la requête
        Optional<Salle> salleOptional = findById(idSalle);
        Optional<Utilisateur> auteurOptional = utilisateurService.findById(idAuteur);

        // Vérifier que les entités existent
        if (salleOptional.isEmpty()) {
            throw new EntityNotFoundException("La salle spécifiée n'existe pas.");
        }
        if (auteurOptional.isEmpty()) {
            throw new EntityNotFoundException("L'utilisateur spécifié n'existe pas.");
        }

        Salle salle = salleOptional.get();
        Utilisateur auteur = auteurOptional.get();

        // Vérifier que la salle et le site sont actifs
        if (!salle.isActif()) {
            throw new SalleInactiveException();
        }
        if (!salle.getSite().isActif()) {
            throw new SiteInactifException();
        }

        // Vérifier qu'une réservation similaire n'existe pas déjà
        Reservation nouvelleReservation = new Reservation();
        nouvelleReservation.setSalle(salle);
        nouvelleReservation.setDate(date);
        nouvelleReservation.setCreneau(creneau);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<Reservation> example = Example.of(nouvelleReservation, exampleMatcher);
        boolean creneauDisponible = !reservationRepository.exists(example);

        if (!creneauDisponible) {
            throw new CreneauIndisponibleException();
        }

        nouvelleReservation.setUtilisateur(auteur);
        reservationRepository.saveAndFlush(nouvelleReservation);
    }

    public boolean setActif(Salle salle, boolean valeur) {
        if (salle.isActif() == valeur) {
            return false;
        }

        salle.setActif(valeur);
        salleRepository.saveAndFlush(salle);
        return true;
    }
}
