package com.doan.product.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @Column(name = "image")
    @ElementCollection
    @JoinTable(name = "images", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> images;

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

    //type = 1 : product
    //type = 2 : service
    @Column(name = "type")
    private byte type;
}
