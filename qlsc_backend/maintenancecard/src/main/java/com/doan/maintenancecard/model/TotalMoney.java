package com.doan.maintenancecard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TotalMoney {

    private String time;
    private BigDecimal total;
    private int quantity;
    private int average;

    public TotalMoney(String time, BigDecimal total) {
        this.time = time;
        this.total = total;
    }

}
