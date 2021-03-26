package com.nk.maintenancecard.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payments")
public class PaymentMethod extends BaseEntity {

    @Column(name = "name", nullable = false,length = 100,unique = true)
    private String name;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }
}
