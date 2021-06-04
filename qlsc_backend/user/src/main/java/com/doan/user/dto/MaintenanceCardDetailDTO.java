package com.doan.user.dto;

import com.doan.user.entity.MaintenanceCard;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceCardDetailDTO extends BaseDTO {

    private MaintenanceCard maintenanceCard;
    private ProductDTO product;
    private byte status;
    private BigDecimal price;
    private int quantity;
}
