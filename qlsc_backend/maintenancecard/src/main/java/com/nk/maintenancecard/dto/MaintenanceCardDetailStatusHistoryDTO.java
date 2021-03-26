package com.nk.maintenancecard.dto;

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
