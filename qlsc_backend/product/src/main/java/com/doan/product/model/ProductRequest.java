package com.doan.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Getter
@Setter
public class ProductRequest {

    private String image;
    private String name;
    private int quantity;
    private String unit;
    private String pricePerUnit;
    private String code;
    private String description;
    private Byte type;
    private byte status;
}
