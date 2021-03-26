package com.nk.product.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Column(name = "name", nullable = false,length = 100,unique = true)
    private String name;

    @Column(name = "code", nullable = false,length = 11,unique = true)
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

    @Column(name = "status",nullable = false)
    private byte status;

    @Column(name = "type")
    private byte type;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
