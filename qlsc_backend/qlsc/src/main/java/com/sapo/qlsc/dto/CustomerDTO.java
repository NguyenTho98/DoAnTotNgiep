package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.Ward;
import com.sapo.qlsc.validation.anotation.CustomerPhone;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    public int getTotalNotPay() {
        return totalNotPay;
    }

    public void setTotalNotPay(int totalNotPay) {
        this.totalNotPay = totalNotPay;
    }

    public BigDecimal getCurrent_debt() {
        return current_debt;
    }

    public void setCurrent_debt(BigDecimal current_debt) {
        this.current_debt = current_debt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WardDTO getWard() {
        return ward;
    }

    public List<MaintenanceCardDTO> getMaintenanceCards() {
        return maintenanceCards;
    }

    public void setMaintenanceCards(List<MaintenanceCardDTO> maintenanceCards) {
        this.maintenanceCards = maintenanceCards;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setWard(WardDTO ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
}
