package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@ToString
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

    private List<MaintenanceCardDTO> repairmanMaintenanceCards;

    private byte role;

    private List<MaintenanceCardDTO> coordinatorMaintenanceCards;

    private int messageNumber;

}
