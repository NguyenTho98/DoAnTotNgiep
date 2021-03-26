package com.sapo.qlsc.dto;

import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.Product;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;

public class MaintenanceCardDetailDTO extends BaseDTO {

    private MaintenanceCardDTO maintenanceCard;

    private ProductDTO product;

    private byte status;

    private BigDecimal price;

    private int quantity;

    public MaintenanceCardDTO getMaintenanceCard() {
        return maintenanceCard;
    }

    public void setMaintenanceCard(MaintenanceCardDTO maintenanceCard) {
        this.maintenanceCard = maintenanceCard;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
