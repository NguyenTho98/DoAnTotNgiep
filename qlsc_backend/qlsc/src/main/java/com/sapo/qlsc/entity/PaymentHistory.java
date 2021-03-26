package com.sapo.qlsc.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_histories")
public class PaymentHistory extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_card_id")
    private MaintenanceCard maintenanceCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @Column(name = "money",nullable = false)
    private BigDecimal money;

    public MaintenanceCard getMaintenanceCard() {
        return maintenanceCard;
    }

    public void setMaintenanceCard(MaintenanceCard maintenanceCard) {
        this.maintenanceCard = maintenanceCard;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
