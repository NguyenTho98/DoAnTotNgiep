package com.doan.maintenancecard.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "maintenance_card_detail_status_histories")
public class MaintenanceCardDetailStatusHistory extends BaseEntity {

    @Column(name = "status")
    private byte status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_card_detail_id")
    private MaintenanceCardDetail maintenanceCardDetail;

}
