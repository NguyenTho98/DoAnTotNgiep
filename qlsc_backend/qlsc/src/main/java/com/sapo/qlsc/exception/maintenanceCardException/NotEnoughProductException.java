package com.sapo.qlsc.exception.maintenanceCardException;

public class NotEnoughProductException extends Exception{

    private String message;

    public NotEnoughProductException(String message) {
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
