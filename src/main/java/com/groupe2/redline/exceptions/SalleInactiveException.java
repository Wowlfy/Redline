package com.groupe2.redline.exceptions;

/**
 * Lancé par SalleService quand une réservation est faite sur une salle inactive
 */
public class SalleInactiveException extends Exception {
    public SalleInactiveException() {
        super();
    }

    public SalleInactiveException(String message) {
        super(message);
    }

    public SalleInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalleInactiveException(Throwable cause) {
        super(cause);
    }

    protected SalleInactiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
