package com.doan.maintenancecard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TotalMoney {

    private String date;
    private BigDecimal totalDayMoney;

}
