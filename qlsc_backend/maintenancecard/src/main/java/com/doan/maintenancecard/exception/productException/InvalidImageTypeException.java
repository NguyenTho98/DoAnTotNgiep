package com.doan.maintenancecard.exception.productException;

public class InvalidImageTypeException extends Exception{
    private String message;

    public InvalidImageTypeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
