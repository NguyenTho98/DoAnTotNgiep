package com.doan.maintenancecard.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessInformationDTO {

    private int totalCustomer;

    private int totalMaintenanceCard;

    private int totalMaintenanceCards;

    private int totalMaintenanceCardSuccess;

    private int totalMaintenanceCardScNotPay;

    private int totalMaintenanceCardScPayed;

    private BigDecimal totalLiabilities;

    private BigDecimal totalMoney;

}
