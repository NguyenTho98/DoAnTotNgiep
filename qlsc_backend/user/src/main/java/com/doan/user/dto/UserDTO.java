package com.doan.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO{

    private String code;
    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private String address;

    private byte status;

    private byte role;

    private int messageNumber;

    private int totalMaintenanceCard;

}
