package com.sapo.qlsc.dto;

import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.Product;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceCardDetailDTO extends BaseDTO {

    private MaintenanceCardDTO maintenanceCard;

    private ProductDTO product;

    private byte status;

    private BigDecimal price;

    private int quantity;

}
