package com.groupe2.redline.configs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.repositories.UtilisateurRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe  de gestion des autorisations
 */
class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManagerImpl;
    private final String secret;
    private final ApplicationContext ctx;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManagerImpl, String secret, ApplicationContext ctx) {
        this.authenticationManagerImpl = authenticationManagerImpl;
        this.secret = secret;
        this.ctx = ctx;
    }


    // Interception d'une demande d'authentification, en récupérant le body de la requête /login
    // Ici au format {username: ..., password: ...}
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            AccountCredentials creds = new ObjectMapper()
                .readValue(request.getInputStream(), AccountCredentials.class);
            return getAuthentication(creds);
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    // Demande au service d'authentification de vérifier si il s'agit d'un login/password valide
    // -> Envoie à SecurityUserDetailsService
    private Authentication getAuthentication(AccountCredentials creds) {
        try {
            return authenticationManagerImpl.authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(),
                    new ArrayList<>()));
        } catch (AuthenticationException e) {
            logger.error(e);
            throw e;
        }
    }

    // Si SecurityUserDetailsService::loadUserByUsername renvoie un UserDetails, LOGIN valide car utilisateur existe en base
    // -> successfulAuthentication
    @Override
    public void successfulAuthentication(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth
    ) {
        // Création d'une date d'expiration
        ZonedDateTime expiration = LocalDateTime.now().plusHours(12).atZone(ZoneId.of("Europe/Paris"));

        // Récupération d'un userRepository via l'application context
        UtilisateurRepository userRepository = ctx.getBean(UtilisateurRepository.class);

        // Récupération des informations de l'utilisateur
        UserDetails principal = (UserDetails) auth.getPrincipal();

        logger.info("successfulAuthentication -> Login of USER ${principal.username}");
        // Récupération de l'utilisateur en base
        Utilisateur byEmail = userRepository.findByMail(principal.getUsername()).get();
        // Création du JWT
        String token = Jwts.builder()
                // SET du subject (identification de l'utilisateur (UNICITE))
                .setSubject(byEmail.getMail())
                // Ajout d'un claim auth pour définir le niveau d'accès du TOKEN
                .claim("auth", byEmail.getRole().getName())
                // Ajout de la date d'expiration
                .setExpiration(Date.from(expiration.toInstant()))
                // Signature du token
                // SignatureAlgorithm.HS256 -> Suivant la taille du secret
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        // Renvoie dans la réponse du JWT
        // Authorization : Bearer ...
        res.setHeader(HttpHeaders.AUTHORIZATION, "Bearer "+token);
    }
}