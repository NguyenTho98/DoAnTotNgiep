package com.nk.user.exception.userException;

public class DuplicateEmailException extends Exception {
    private String message;

    public DuplicateEmailException(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
