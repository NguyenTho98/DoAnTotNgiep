package com.doan.maintenancecard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessToday {

    private int totalMaintenanceCard;
    private int totalMaintenanceCardByDay;
    private int totalMaintenanceCardSuccess;
    private int totalMaintenanceCardScNotPay;
    private int totalMaintenanceCardScPayed;
}
