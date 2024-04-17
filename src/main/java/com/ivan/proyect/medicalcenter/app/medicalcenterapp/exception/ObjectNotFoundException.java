package com.ivan.proyect.medicalcenter.app.medicalcenterapp.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String name, Throwable message) {
        super(name, message);
    }
}
