package com.doan.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordRequest {
    String oldPassword;
    String password;
    Long id;
}
