package com.doan.maintenancecard.entity;

import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentMethod extends BaseEntity {

    @Column(name = "name", nullable = false,length = 100,unique = true)
    private String name;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistories;
}
