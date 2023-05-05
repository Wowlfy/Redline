package com.groupe2.redline.exceptions;

/**
 * Lancé par SalleService quand une réservation est faite sur un créneau indisponible (= une réservation existe déjà sur ce créneau)
 */
public class CreneauIndisponibleException extends Exception {
    public CreneauIndisponibleException() {
        super();
    }

    public CreneauIndisponibleException(String message) {
        super(message);
    }

    public CreneauIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreneauIndisponibleException(Throwable cause) {
        super(cause);
    }

    protected CreneauIndisponibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
