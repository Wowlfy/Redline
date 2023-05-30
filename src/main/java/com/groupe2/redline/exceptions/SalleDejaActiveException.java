package com.groupe2.redline.exceptions;

/**
 * Lancé par SalleService lors de l'activation d'une salle déjà active
 */
public class SalleDejaActiveException extends Exception {
    public SalleDejaActiveException() {
        super();
    }

    public SalleDejaActiveException(String message) {
        super(message);
    }

    public SalleDejaActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalleDejaActiveException(Throwable cause) {
        super(cause);
    }

    protected SalleDejaActiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
