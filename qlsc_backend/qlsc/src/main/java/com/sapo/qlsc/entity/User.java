package com.sapo.qlsc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.qlsc.validation.anotation.CustomerPhone;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "code", nullable = false,length = 11,unique = true)
    private String code;
    @NotBlank
    @NotNull
    @Column(name = "email", nullable = false,length = 100,unique = true)
    private String email;
    @NotBlank
    @NotNull
    @Column(name = "password", nullable = false,length = 255)
    private String password;
    @NotNull
    @Column(name = "full_name", nullable = false,length = 100)
    private String fullName;
    @NotBlank
    @Column(name = "phone_number", nullable = false,length = 10)
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "text(5000)")
    private String address;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 0")
    private byte status;

    @OneToMany(mappedBy = "repairman",cascade = CascadeType.ALL)
    private List<MaintenanceCard> repairmanMaintenanceCards;

    @OneToMany(mappedBy = "coordinator",cascade = CascadeType.ALL)
    private List<MaintenanceCard> coordinatorMaintenanceCards;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Message> messages;

    @Column(name = "role")
    private byte role;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MaintenanceCard> getRepairmanMaintenanceCards() {
        return repairmanMaintenanceCards;
    }

    public void setRepairmanMaintenanceCards(List<MaintenanceCard> repairmanMaintenanceCards) {
        this.repairmanMaintenanceCards = repairmanMaintenanceCards;
    }

    public List<MaintenanceCard> getCoordinatorMaintenanceCards() {
        return coordinatorMaintenanceCards;
    }

    public void setCoordinatorMaintenanceCards(List<MaintenanceCard> coordinatorMaintenanceCards) {
        this.coordinatorMaintenanceCards = coordinatorMaintenanceCards;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }



}
