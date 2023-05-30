package com.groupe2.redline.exceptions;

/**
 * Lancé par SalleService lors d'une réservation sur un site inactif
 */
public class SiteInactifException extends Exception {
    public SiteInactifException() {
        super();
    }

    public SiteInactifException(String message) {
        super(message);
    }

    public SiteInactifException(String message, Throwable cause) {
        super(message, cause);
    }

    public SiteInactifException(Throwable cause) {
        super(cause);
    }

    protected SiteInactifException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
