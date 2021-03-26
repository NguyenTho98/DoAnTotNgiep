package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.PaymentMethod;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class PaymentHistoryDTO extends BaseDTO{

    private MaintenanceCardDTO maintenanceCard;

    private PaymentMethodDTO paymentMethod;

    private BigDecimal money;

    public MaintenanceCardDTO getMaintenanceCard() {
        return maintenanceCard;
    }

    public void setMaintenanceCard(MaintenanceCardDTO maintenanceCard) {
        this.maintenanceCard = maintenanceCard;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public PaymentHistoryDTO() {
    }
}
