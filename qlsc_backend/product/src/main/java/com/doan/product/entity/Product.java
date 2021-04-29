package com.doan.product.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "code", nullable = false, length = 11, unique = true)
    private String code;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "quantity", columnDefinition = "int default 0")
    private int quantity;

    @Column(name = "unit", length = 100)
    private String unit;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column(name = "description", columnDefinition = "text(5000)")
    private String description;

    @Column(name = "status", nullable = false)
    private byte status;

    @Column(name = "type")
    private byte type;
}
