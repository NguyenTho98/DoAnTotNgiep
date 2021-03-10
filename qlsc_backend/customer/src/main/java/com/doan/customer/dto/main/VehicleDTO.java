package com.doan.customer.dto.main;

import com.doan.customer.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO extends BaseDTO {

    private String color;
    private String model;
    private String plateNumber;

}
