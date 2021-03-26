package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WardDTO {

    public String name;

    public String code;

    public DistrictDTO district;

}
