package com.sapo.qlsc.dto;

import java.math.BigDecimal;

public class BusinessInformationDTO {

    private int totalCustomer;

    private int totalMaintenanceCard;

    private int totalMaintenanceCards;

    private int totalMaintenanceCardSuccess;

    private int totalMaintenanceCardScNotPay;

    private int totalMaintenanceCardScPayed;

    private BigDecimal totalLiabilities;

    private BigDecimal totalMoney;

    public BusinessInformationDTO() {
    }

    public BigDecimal getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(BigDecimal totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public int getTotalMaintenanceCardScPayed() {
        return totalMaintenanceCardScPayed;
    }

    public void setTotalMaintenanceCardScPayed(int totalMaintenanceCardScPayed) {
        this.totalMaintenanceCardScPayed = totalMaintenanceCardScPayed;
    }

    public int getTotalMaintenanceCards() {
        return totalMaintenanceCards;
    }

    public void setTotalMaintenanceCards(int totalMaintenanceCards) {
        this.totalMaintenanceCards = totalMaintenanceCards;
    }

    public int getTotalMaintenanceCardScNotPay() {
        return totalMaintenanceCardScNotPay;
    }

    public void setTotalMaintenanceCardScNotPay(int totalMaintenanceCardScNotPay) {
        this.totalMaintenanceCardScNotPay = totalMaintenanceCardScNotPay;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(int totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public int getTotalMaintenanceCard() {
        return totalMaintenanceCard;
    }

    public void setTotalMaintenanceCard(int totalMaintenanceCard) {
        this.totalMaintenanceCard = totalMaintenanceCard;
    }

    public int getTotalMaintenanceCardSuccess() {
        return totalMaintenanceCardSuccess;
    }

    public void setTotalMaintenanceCardSuccess(int totalMaintenanceCardSuccess) {
        this.totalMaintenanceCardSuccess = totalMaintenanceCardSuccess;
    }
}
