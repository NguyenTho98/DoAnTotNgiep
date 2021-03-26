package com.doan.maintenancecard.exception.productException;

public class ProductNotFoundException extends Exception{
    private String message;

    public ProductNotFoundException(String message) {
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
