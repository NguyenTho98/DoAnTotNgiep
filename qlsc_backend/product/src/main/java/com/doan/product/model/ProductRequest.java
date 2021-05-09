package com.doan.product.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductRequest {

    private List<String> images;
    private String name;
    private int quantity;
    private String unit;
    private String pricePerUnit;
    private String code;
    private String description;
    private Byte type;
    private byte status;
}
