package com.nk.maintenancecard.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "maintenance_card_details")
public class MaintenanceCardDetail extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_card_id")
    private MaintenanceCard maintenanceCard;

    @JoinColumn(name = "product_id")
    private long productId;

    @Column(name = "product_name", nullable = false,length = 100)
    private String productName;

    @Column(name = "product_code", nullable = false,length = 11)
    private String productCode;

    @Column(name = "product_image", length = 255)
    private String productImage;

    @Column(name = "product_unit", length = 100)
    private String productUnit;

    @Column(name = "product_type")
    private byte productType;

    @Column(name = "product_price_per_unit")
    private BigDecimal productPricePerUnit;

    @Column(name = "status")
    private byte status;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_delete")
    private byte isDelete;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "maintenanceCardDetail",fetch = FetchType.LAZY)
    private List<MaintenanceCardDetailStatusHistory> maintenanceCardDetailStatusHistories;

    public BigDecimal getProductPricePerUnit() {
        return productPricePerUnit;
    }

    public void setProductPricePerUnit(BigDecimal productPricePerUnit) {
        this.productPricePerUnit = productPricePerUnit;
    }

    public List<MaintenanceCardDetailStatusHistory> getMaintenanceCardDetailStatusHistories() {
        return maintenanceCardDetailStatusHistories;
    }

    public void setMaintenanceCardDetailStatusHistories(List<MaintenanceCardDetailStatusHistory> maintenanceCardDetailStatusHistories) {
        this.maintenanceCardDetailStatusHistories = maintenanceCardDetailStatusHistories;
    }

    public MaintenanceCard getMaintenanceCard() {
        return maintenanceCard;
    }

    public void setMaintenanceCard(MaintenanceCard maintenanceCard) {
        this.maintenanceCard = maintenanceCard;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public byte getProductType() {
        return productType;
    }

    public void setProductType(byte productType) {
        this.productType = productType;
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

    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }
}
