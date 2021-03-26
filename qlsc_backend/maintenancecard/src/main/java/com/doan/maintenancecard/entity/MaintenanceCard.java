package com.doan.maintenancecard.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
