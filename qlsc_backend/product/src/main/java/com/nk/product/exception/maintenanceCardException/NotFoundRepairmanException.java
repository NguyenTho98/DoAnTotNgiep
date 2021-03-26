package com.nk.product.exception.maintenanceCardException;

public class NotFoundRepairmanException extends Exception{
    private String message;

    public NotFoundRepairmanException(String message) {
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
