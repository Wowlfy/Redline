package com.groupe2.redline.services;

import com.groupe2.redline.dto.DemandeDto;
import com.groupe2.redline.entities.Demande;
import com.groupe2.redline.mappers.DemandeMapper;
import com.groupe2.redline.repositories.DemandeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class DemandeService {

    private final DemandeRepository demandeRepository;

    private final DemandeMapper demandeMapper;

    public DemandeService(DemandeRepository demandeRepository, DemandeMapper demandeMapper) {
        this.demandeRepository = demandeRepository;
        this.demandeMapper = demandeMapper;
    }

    /**
     * Créer et enregistrer une demande
     * @return L'entité créée
     */
    public Demande creer(
            DemandeDto demandeDto
    ) {
        Demande updatedDemande = demandeMapper.createDemandeFromDto(demandeDto);

        return demandeRepository.saveAndFlush(updatedDemande);
    }
}
