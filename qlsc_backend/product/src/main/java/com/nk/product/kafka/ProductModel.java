package com.nk.product.kafka;

public class ProductModel {
    private int amountChargeInUnit;
    private String code;
    private int status;

    public int getAmountChargeInUnit() {
        return amountChargeInUnit;
    }

    public void setAmountChargeInUnit(int amountChargeInUnit) {
        this.amountChargeInUnit = amountChargeInUnit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
