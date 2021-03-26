package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;
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

    private String description;

    private byte status;

    private byte type;

    private List<MaintenanceCardDetailDTO> maintenanceCardDetailDTOS;

}
