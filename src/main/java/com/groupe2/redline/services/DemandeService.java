package com.groupe2.redline.services;

import com.groupe2.redline.entities.Demande;
import com.groupe2.redline.repository.DemandeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;

    /**
     * Créer et enregistrer une demande
     * @return L'entité créée
     */
    public Demande creer(
            String nom,
            String email,
            int duree,
            Date proposition1,
            Date proposition2,
            Date proposition3,
            String description
    ) {
        Demande nouvelleDemande = new Demande();
        nouvelleDemande.setNomDemandeur(nom);
        nouvelleDemande.setEmail(email);
        nouvelleDemande.setDuree(duree);
        nouvelleDemande.setPropositionDate1(proposition1);
        nouvelleDemande.setPropositionDate2(proposition2);
        nouvelleDemande.setPropositionDate3(proposition3);
        nouvelleDemande.setDescription(description);

        return demandeRepository.saveAndFlush(nouvelleDemande);
    }
}
