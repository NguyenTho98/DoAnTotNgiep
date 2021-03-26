package com.nk.maintenancecard.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "maintenance_cards")
public class MaintenanceCard extends BaseEntity {

    @Column(name = "code", nullable = false,length = 11,unique = true)
    private String code;

    @Column(name = "plates_number", nullable = false,length = 11)
    private String platesNumber;

    @JoinColumn(name = "customer_id")
    private long customerId;

    @JoinColumn(name = "customer_name")
    private String customerName;

    @JoinColumn(name = "customer_phone")
    private String customerPhone;

    @JoinColumn(name = "repairman_id")
    private long repairmanId;

    @JoinColumn(name = "repairman_name")
    private String repairmanName;

    @JoinColumn(name = "repairman_email")
    private String repairmanEmail;

    @JoinColumn(name = "coordinator_id")
    private long coordinatorId;

    @JoinColumn(name = "coordinator_name")
    private String coordinatorName;

    @JoinColumn(name = "coordinator_email")
    private String coordinatorEmail;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expected_return_date", length = 19)
    private Date expectedReturnDate;

    @OneToMany(mappedBy = "maintenanceCard", cascade = CascadeType.ALL)
    private List<MaintenanceCardDetail> maintenanceCardDetails;

    @OneToMany(mappedBy = "maintenanceCard", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistories;

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getRepairmanEmail() {
        return repairmanEmail;
    }

    public void setRepairmanEmail(String repairmanEmail) {
        this.repairmanEmail = repairmanEmail;
    }

    public String getCoordinatorEmail() {
        return coordinatorEmail;
    }

    public void setCoordinatorEmail(String coordinatorEmail) {
        this.coordinatorEmail = coordinatorEmail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlatesNumber() {
        return platesNumber;
    }

    public void setPlatesNumber(String platesNumber) {
        this.platesNumber = platesNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public long getRepairmanId() {
        return repairmanId;
    }

    public void setRepairmanId(long repairmanId) {
        this.repairmanId = repairmanId;
    }

    public String getRepairmanName() {
        return repairmanName;
    }

    public void setRepairmanName(String repairmanName) {
        this.repairmanName = repairmanName;
    }

    public long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
