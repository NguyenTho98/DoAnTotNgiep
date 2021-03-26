package com.nk.maintenancecard.dto;

import com.nk.maintenancecard.entity.MaintenanceCardDetail;
import com.nk.maintenancecard.entity.PaymentHistory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MaintenanceCardDTO extends BaseDTO {
    private String code;

    private String platesNumber;

    private CustomerDTO customer;

    private UserDTO repairman;

    private UserDTO coordinator;

    private String description;

    private Date returnDate;

    private BigDecimal price;

    private byte workStatus;

    private byte payStatus;

    private String model;

    private String color;

    private Date expectedReturnDate;

    private List<MaintenanceCardDetailDTO> maintenanceCardDetails;

    private List<PaymentHistoryDTO> paymentHistories;

    private List<MaintenanceCardDetailStatusHistoryDTO> maintenanceCardDetailStatusHistories;

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public List<MaintenanceCardDetailStatusHistoryDTO> getMaintenanceCardDetailStatusHistories() {
        return maintenanceCardDetailStatusHistories;
    }

    public void setMaintenanceCardDetailStatusHistories(List<MaintenanceCardDetailStatusHistoryDTO> maintenanceCardDetailStatusHistories) {
        this.maintenanceCardDetailStatusHistories = maintenanceCardDetailStatusHistories;
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public UserDTO getRepairman() {
        return repairman;
    }

    public void setRepairman(UserDTO repairman) {
        this.repairman = repairman;
    }

    public UserDTO getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(UserDTO coordinator) {
        this.coordinator = coordinator;
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

    public List<MaintenanceCardDetailDTO> getMaintenanceCardDetails() {
        return maintenanceCardDetails;
    }

    public void setMaintenanceCardDetails(List<MaintenanceCardDetailDTO> maintenanceCardDetails) {
        this.maintenanceCardDetails = maintenanceCardDetails;
    }

    public List<PaymentHistoryDTO> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistoryDTO> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }

    public MaintenanceCardDTO() {
    }
}
