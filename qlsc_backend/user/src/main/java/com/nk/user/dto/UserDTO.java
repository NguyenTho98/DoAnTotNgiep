package com.nk.user.dto;


import javax.persistence.Column;
import java.util.List;

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

    public int getTotalMaintenanceCard() {
        return totalMaintenanceCard;
    }

    public void setTotalMaintenanceCard(int totalMaintenanceCard) {
        this.totalMaintenanceCard = totalMaintenanceCard;
    }

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

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }
}
