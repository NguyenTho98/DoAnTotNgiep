package com.doan.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String code;
    private boolean success;
    private String message;

    public UserResponse(boolean success) {
        this.success = success;
    }

    public UserResponse(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public UserResponse(boolean success, String message, String code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }
}
