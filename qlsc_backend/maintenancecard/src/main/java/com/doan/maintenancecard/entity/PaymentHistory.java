package com.doan.maintenancecard.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_histories")
public class PaymentHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_card_id")
    private MaintenanceCard maintenanceCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @Column(name = "money", nullable = false)
    private BigDecimal money;

}
