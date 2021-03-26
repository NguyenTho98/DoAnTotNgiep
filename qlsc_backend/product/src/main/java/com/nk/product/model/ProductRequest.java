package com.nk.product.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class ProductRequest {

    private Optional<MultipartFile> image;
    private String name;
    private Optional<String> quantity;
    private Optional<String> unit;
    private Optional<String> pricePerUnit;
    private Optional<String> code;
    private String description;
    private String type;
    private Optional<String> status;

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }

    public Optional<MultipartFile> getImage() {
        return image;
    }

    public void setImage(Optional<MultipartFile> image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(Optional<String> quantity) {
        this.quantity = quantity;
    }

    public Optional<String> getUnit() {
        return unit;
    }

    public void setUnit(Optional<String> unit) {
        this.unit = unit;
    }

    public Optional<String> getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Optional<String> pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Optional<String> getCode() {
        return code;
    }

    public void setCode(Optional<String> code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
