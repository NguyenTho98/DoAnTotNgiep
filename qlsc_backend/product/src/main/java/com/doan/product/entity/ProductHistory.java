package com.doan.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
