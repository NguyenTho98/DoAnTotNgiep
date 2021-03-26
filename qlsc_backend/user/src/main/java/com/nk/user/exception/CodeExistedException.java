package com.nk.user.exception;

public class CodeExistedException extends Exception{
    private String message;

    public CodeExistedException(String message) {
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
