package com.groupe2.redline.exceptions;

public class UtilisateurDejaPresentException extends Exception{

    public UtilisateurDejaPresentException() { super();}
    public UtilisateurDejaPresentException(String message) { super(message); }
    public UtilisateurDejaPresentException(String message, Throwable cause) { super(message, cause); }
    public UtilisateurDejaPresentException(Throwable cause) {
        super(cause);
    }
    protected UtilisateurDejaPresentException(String message, Throwable cause, boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
