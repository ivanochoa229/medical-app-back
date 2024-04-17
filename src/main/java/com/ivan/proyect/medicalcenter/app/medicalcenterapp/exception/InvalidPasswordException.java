package com.ivan.proyect.medicalcenter.app.medicalcenterapp.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
