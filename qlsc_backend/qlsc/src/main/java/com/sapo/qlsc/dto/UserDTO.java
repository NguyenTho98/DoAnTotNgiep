package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@ToString
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

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
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

    public List<MaintenanceCardDTO> getRepairmanWarrantyCards() {
        return repairmanMaintenanceCards;
    }

    public void setRepairmanWarrantyCards(List<MaintenanceCardDTO> repairmanMaintenanceCards) {
        this.repairmanMaintenanceCards = repairmanMaintenanceCards;
    }

    public List<MaintenanceCardDTO> getRepairmanMaintenanceCards() {
        return repairmanMaintenanceCards;
    }

    public void setRepairmanMaintenanceCards(List<MaintenanceCardDTO> repairmanMaintenanceCards) {
        this.repairmanMaintenanceCards = repairmanMaintenanceCards;
    }

    public List<MaintenanceCardDTO> getCoordinatorMaintenanceCards() {
        return coordinatorMaintenanceCards;
    }

    public void setCoordinatorMaintenanceCards(List<MaintenanceCardDTO> coordinatorMaintenanceCards) {
        this.coordinatorMaintenanceCards = coordinatorMaintenanceCards;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }
}
