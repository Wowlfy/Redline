package com.groupe2.redline.exceptions;

/**
 * Lancé par SiteService lorsque l'on tente d'activer un site déjà actif
 */
public class SiteDejaActifException extends Exception {
    public SiteDejaActifException() {
        super();
    }

    public SiteDejaActifException(String message) {
        super(message);
    }

    public SiteDejaActifException(String message, Throwable cause) {
        super(message, cause);
    }

    public SiteDejaActifException(Throwable cause) {
        super(cause);
    }

    protected SiteDejaActifException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
