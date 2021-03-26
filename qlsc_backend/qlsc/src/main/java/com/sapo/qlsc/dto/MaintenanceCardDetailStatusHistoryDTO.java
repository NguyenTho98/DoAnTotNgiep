package com.sapo.qlsc.dto;

import com.sapo.qlsc.entity.MaintenanceCardDetail;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MaintenanceCardDetailStatusHistoryDTO extends BaseDTO{

    private byte status;

    private String name;

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
