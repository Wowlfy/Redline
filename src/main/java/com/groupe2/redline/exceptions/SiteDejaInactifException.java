package com.groupe2.redline.exceptions;

/**
 * Lancé par SiteService lorsque l'on tente de désactiver un site déjà inactif
 */
public class SiteDejaInactifException extends Exception {
    public SiteDejaInactifException() {
        super();
    }

    public SiteDejaInactifException(String message) {
        super(message);
    }

    public SiteDejaInactifException(String message, Throwable cause) {
        super(message, cause);
    }

    public SiteDejaInactifException(Throwable cause) {
        super(cause);
    }

    protected SiteDejaInactifException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
