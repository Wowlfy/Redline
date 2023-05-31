package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.UtilisateurDto;
import com.groupe2.redline.entities.Role;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UtilisateurMapper(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur createUtilisateurFromDto(UtilisateurDto dto) {
        Utilisateur nouvelUtilisateur = new Utilisateur();
        nouvelUtilisateur.setNom(dto.getNom());
        nouvelUtilisateur.setMail(dto.getMail());
        nouvelUtilisateur.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        Role role = roleRepository.findById(dto.getIdRole()).orElseThrow(EntityNotFoundException::new);
        nouvelUtilisateur.setRole(role);

        return nouvelUtilisateur;
    }
}
