package com.groupe2.redline.configs;


import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.repositories.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Re définition du [UserDetailsService] pour le mécanisme d'authentification
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {


    private final UtilisateurRepository utilisateurRepository;

    public SecurityUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Utilisateur> account = utilisateurRepository.findByMail(username);
        if (account.isPresent()) {
            return account.get();
        }
        throw new UsernameNotFoundException("the username $username doesn't exist");
    }
}