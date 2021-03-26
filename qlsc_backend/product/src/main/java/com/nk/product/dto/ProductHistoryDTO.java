package com.nk.product.dto;

import javax.persistence.Column;

public class ProductHistoryDTO extends BaseDTO{

    private int amountChargeInUnit;

    private String name;

    private Long productId;

    private String note;

    private int stockRemain;

    public int getAmountChargeInUnit() {
        return amountChargeInUnit;
    }

    public void setAmountChargeInUnit(int amountChargeInUnit) {
        this.amountChargeInUnit = amountChargeInUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStockRemain() {
        return stockRemain;
    }

    public void setStockRemain(int stockRemain) {
        this.stockRemain = stockRemain;
    }
}
