package com.doan.maintenancecard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends BaseDTO {

    private String name;
    private String phoneNumber;
    private String code;
    private String email;
}
