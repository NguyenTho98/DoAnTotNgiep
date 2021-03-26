package com.doan.maintenancecard.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalMoneyDTO {

    private String date;

    private BigDecimal totalDayMoney;

    public TotalMoneyDTO() {
    }

    public TotalMoneyDTO(String date, BigDecimal totalDayMoney) {
        this.date = date;
        this.totalDayMoney = totalDayMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotalDayMoney() {
        return totalDayMoney;
    }

    public void setTotalDayMoney(BigDecimal totalDayMoney) {
        this.totalDayMoney = totalDayMoney;
    }
}
