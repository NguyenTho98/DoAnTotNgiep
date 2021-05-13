package com.doan.maintenancecard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalMoney {

    private String time;
    private BigDecimal total;
    private String dateText;

    public TotalMoney(String time, BigDecimal total) {
        this.time = time;
        this.total = total;
    }

}
