package com.nk.maintenancecard.entity;

import javax.persistence.*;

@Entity
@Table(name = "maintenance_card_detail_status_histories")
public class MaintenanceCardDetailStatusHistory extends BaseEntity {

    @Column(name = "status")
    private byte status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_card_detail_id")
    private MaintenanceCardDetail maintenanceCardDetail;

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public MaintenanceCardDetail getMaintenanceCardDetail() {
        return maintenanceCardDetail;
    }

    public void setMaintenanceCardDetail(MaintenanceCardDetail maintenanceCardDetail) {
        this.maintenanceCardDetail = maintenanceCardDetail;
    }
}
