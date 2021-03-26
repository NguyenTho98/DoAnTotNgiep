package com.sapo.qlsc.entity;

import com.sapo.qlsc.dto.CustomerDTO;
import com.sapo.qlsc.dto.WardDTO;
import com.sapo.qlsc.validation.anotation.CustomerPhone;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Column(name = "name", nullable = false,length = 100)
    private String name;

    @Column(name = "phone_number", nullable = false,length = 11)
    @CustomerPhone
    private String phoneNumber;

    @Column(name = "code", length = 11,unique = true, nullable = false)
    private String code;

    @Column(name = "email",length = 100)
    private String email;

    @Column(name = "description", columnDefinition = "text(5000)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ward_code")
    private Ward ward;

    @Column(name = "address", columnDefinition = "text(5000)")
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<MaintenanceCard> maintenanceCards;

    @Column(name = "status",nullable = false)
    private byte status;

    public Customer() {
    }

    public void setCustomer(CustomerDTO customerDTO){
        this.code = customerDTO.getCode();
        this.name = customerDTO.getName();
        this.phoneNumber = customerDTO.getPhoneNumber();
        this.email = customerDTO.getEmail();
        this.description = customerDTO.getDescription();
        this.status = customerDTO.getStatus();
        this.address = customerDTO.getAddress();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public List<MaintenanceCard> getMaintenanceCards() {
        return maintenanceCards;
    }

    public void setMaintenanceCards(List<MaintenanceCard> maintenanceCards) {
        this.maintenanceCards = maintenanceCards;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
