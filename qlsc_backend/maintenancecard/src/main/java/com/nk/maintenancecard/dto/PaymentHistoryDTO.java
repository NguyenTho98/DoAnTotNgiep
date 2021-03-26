package com.nk.maintenancecard.dto;

import java.math.BigDecimal;

public class PaymentHistoryDTO extends BaseDTO {

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
