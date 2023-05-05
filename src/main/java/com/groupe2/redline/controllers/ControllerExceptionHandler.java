package com.groupe2.redline.controllers;

import com.groupe2.redline.exceptions.SalleDejaActiveException;
import com.groupe2.redline.exceptions.SalleDejaInactiveException;
import com.groupe2.redline.exceptions.SiteDejaActifException;
import com.groupe2.redline.exceptions.SiteDejaInactifException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Intercepte certaines erreurs lancées par les Controllers et renvoie une réponse appropriée au client
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    // TODO : Renvoyer une réponse structurée en json
    // Erreurs 500 par défaut :
    /*
        {
            "timestamp": "2023-05-05T12:49:11.156+00:00",
            "status": 500,
            "error": "Internal Server Error",
            "path": "/api/site/get/1/desactiver"
        }
     */

    /**
     * Traiter les exceptions EntityNotFound comme erreurs 404
     *
     * @return Erreur 404
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundHandler(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    /**
     * Traiter les exceptions SiteDejaActif comme erreurs 409
     *
     * @return Erreur 409
     */
    @ExceptionHandler(SiteDejaActifException.class)
    public ResponseEntity<String> siteDejaActifHandler(SiteDejaActifException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    /**
     * Traiter les exceptions SiteDejaInactif comme erreurs 409
     *
     * @return Erreur 409
     */
    @ExceptionHandler(SiteDejaInactifException.class)
    public ResponseEntity<String> siteDejaInactifHandler(SiteDejaInactifException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    /**
     * Traiter les exceptions SalleDejaInactive comme erreurs 409
     *
     * @return Erreur 409
     */
    @ExceptionHandler(SalleDejaInactiveException.class)
    public ResponseEntity<String> salleDejaInactiveHandler(SalleDejaInactiveException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    /**
     * Traiter les exceptions SalleDejaActive comme erreurs 409
     *
     * @return Erreur 409
     */
    @ExceptionHandler(SalleDejaActiveException.class)
    public ResponseEntity<String> salleDejaActiveHandler(SalleDejaActiveException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    /**
     * Traiter les exceptions MethodArgumentNotValid comme erreurs 400
     *
     * @return Erreur 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> validationHandler(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        List<String> messages = errors.stream()
                .map(ObjectError::toString)
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
    }
}
