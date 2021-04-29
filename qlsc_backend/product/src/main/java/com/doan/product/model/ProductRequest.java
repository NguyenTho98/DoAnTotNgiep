package com.doan.product.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Getter
@Setter
public class ProductRequest {

    private MultipartFile image;
    private String name;
    private int quantity;
    private String unit;
    private String pricePerUnit;
    private String code;
    private String description;
    private String type;
    private byte status;
}
