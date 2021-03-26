package com.sapo.qlsc.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "maintenance_cards")
public class MaintenanceCard extends BaseEntity{

    @Column(name = "code", nullable = false,length = 11,unique = true)
    private String code;

    @Column(name = "plates_number", nullable = false,length = 11)
    private String platesNumber;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repairman_id")
    private User repairman;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator_id")
    private User coordinator;

    @Column(name = "description", columnDefinition = "text(5000)")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date", length = 19)
    private Date returnDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "work_status")
    private byte workStatus;

    @Column(name = "pay_status")
    private byte payStatus;

    @Column(name = "model", length = 50)
    private String model;

    @Column(name = "color", length = 50)
    private String color;
//
//    @Column(name = "is_delete", length = 50)
//    private int isDelete;
//
//    public int getIsDelete() {
//        return isDelete;
//    }
//
//    public void setIsDelete(int isDelete) {
//        this.isDelete = isDelete;
//    }
//
//    public void setIsDelete(int isDelete) {
//        this.isDelete = isDelete;
//    }

    @OneToMany(mappedBy = "maintenanceCard", cascade = CascadeType.ALL)
    private List<MaintenanceCardDetail> maintenanceCardDetails;

    @OneToMany(mappedBy = "maintenanceCard", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistories;

    public List<MaintenanceCardDetail> getMaintenanceCardDetails() {
        return maintenanceCardDetails;
    }

    public void setMaintenanceCardDetails(List<MaintenanceCardDetail> maintenanceCardDetails) {
        this.maintenanceCardDetails = maintenanceCardDetails;
    }

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlatesNumber() {
        return platesNumber;
    }

    public void setPlatesNumber(String platesNumber) {
        this.platesNumber = platesNumber;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getRepairman() {
        return repairman;
    }

    public void setRepairman(User repairman) {
        this.repairman = repairman;
    }

    public User getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(User coordinator) {
        this.coordinator = coordinator;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(byte workStatus) {
        this.workStatus = workStatus;
    }

    public byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(byte payStatus) {
        this.payStatus = payStatus;
    }
}
