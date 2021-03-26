package com.doan.product.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Getter
@Setter
public class ProductRequest {

    private Optional<MultipartFile> image;
    private String name;
    private Optional<String> quantity;
    private Optional<String> unit;
    private Optional<String> pricePerUnit;
    private Optional<String> code;
    private String description;
    private String type;
    private Optional<String> status;
}
