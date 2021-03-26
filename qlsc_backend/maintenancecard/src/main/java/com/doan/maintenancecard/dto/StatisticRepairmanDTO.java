package com.doan.maintenancecard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticRepairmanDTO {

    private String name;

    private int totalMaintenanceCard;

    public StatisticRepairmanDTO() {
    }

    public StatisticRepairmanDTO(String name, int totalMaintenanceCard) {
        this.name = name;
        this.totalMaintenanceCard = totalMaintenanceCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalMaintenanceCard() {
        return totalMaintenanceCard;
    }

    public void setTotalMaintenanceCard(int totalMaintenanceCard) {
        this.totalMaintenanceCard = totalMaintenanceCard;
    }
}
