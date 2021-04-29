package com.doan.maintenancecard.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseDTO {

    private String name;
    private String code;
    private String image;
    private int quantity;
    private String unit;
    private BigDecimal pricePerUnit;
    private byte type;

}
