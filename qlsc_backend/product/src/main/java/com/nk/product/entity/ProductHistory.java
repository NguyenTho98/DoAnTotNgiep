package com.nk.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_histories")
public class ProductHistory extends BaseEntity{

    @Column(name = "amount_charge_in_unit")
    private int amountChargeInUnit;

    @Column(name = "name")
    private String name;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "note")
    private String note;

    @Column(name = "stock_remain")
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
