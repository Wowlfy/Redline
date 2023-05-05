package com.groupe2.redline.exceptions;

/**
 * Lancé par SalleService lors de la désactivation d'une salle déjà inactive
 */
public class SalleDejaInactiveException extends Exception {
    public SalleDejaInactiveException() {
        super();
    }

    public SalleDejaInactiveException(String message) {
        super(message);
    }

    public SalleDejaInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalleDejaInactiveException(Throwable cause) {
        super(cause);
    }

    protected SalleDejaInactiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
