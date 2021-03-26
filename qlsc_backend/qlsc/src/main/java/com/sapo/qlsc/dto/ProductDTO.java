package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

public class ProductDTO extends BaseDTO {

    private String name;

    private String code;

    private String image;

    private int quantity;

    private String unit;

    private BigDecimal pricePerUnit;

    private String description;

    private byte status;

    private byte type;

    private List<MaintenanceCardDetailDTO> maintenanceCardDetailDTOS;

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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public List<MaintenanceCardDetailDTO> getMaintenanceCardDetailDTOS() {
        return maintenanceCardDetailDTOS;
    }

    public void setMaintenanceCardDetailDTOS(List<MaintenanceCardDetailDTO> maintenanceCardDetailDTOS) {
        this.maintenanceCardDetailDTOS = maintenanceCardDetailDTOS;
    }
}
