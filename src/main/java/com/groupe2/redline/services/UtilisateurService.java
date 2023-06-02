package com.groupe2.redline.services;

import com.groupe2.redline.dto.UtilisateurDto;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.exceptions.UtilisateurDejaPresentException;
import com.groupe2.redline.mappers.UtilisateurMapper;
import com.groupe2.redline.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    public Utilisateur addUtilisateur(UtilisateurDto dto) throws UtilisateurDejaPresentException {
        if (utilisateurRepository.existsByMail(dto.getMail())) {throw new UtilisateurDejaPresentException("Adresse email déjà présente dans la base de données"); }
            Utilisateur nouvelUtilisateur = utilisateurMapper.createUtilisateurFromDto(dto);
            return utilisateurRepository.save(nouvelUtilisateur);
    }
}
