package com.groupe2.redline.services;

import com.groupe2.redline.entities.Demande;
import com.groupe2.redline.repository.DemandeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;

    public Demande addDemande(Demande demande) {
        return demandeRepository.saveAndFlush(demande);
    }
}
