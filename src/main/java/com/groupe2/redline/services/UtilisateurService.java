package com.groupe2.redline.services;

import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }
}
