package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.Ward;
import com.sapo.qlsc.validation.anotation.CustomerPhone;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends BaseDTO {
    private String name;

    private String phoneNumber;

    private String code;

    private String email;

    private String description;

    private WardDTO ward;

    private String address;

    @JsonIgnore
    private List<MaintenanceCardDTO> maintenanceCards;

    private byte status;

    private String pay_status;

    private int totalNotPay;

    private BigDecimal current_debt;

}
