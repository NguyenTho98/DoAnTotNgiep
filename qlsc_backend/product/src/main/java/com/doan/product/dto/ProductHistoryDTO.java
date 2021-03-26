package com.doan.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductHistoryDTO extends BaseDTO{

    private int amountChargeInUnit;

    private String name;

    private Long productId;

    private String note;

    private int stockRemain;

}
