package com.nk.maintenancecard.exception;

public class NotANumberException extends Exception{
    private String message;

    public NotANumberException(String message) {
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
