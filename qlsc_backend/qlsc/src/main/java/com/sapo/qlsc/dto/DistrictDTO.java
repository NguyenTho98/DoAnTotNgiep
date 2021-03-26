package com.sapo.qlsc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDTO {

    public String name;

    public String code;

    public ProvinceDTO province;
}
