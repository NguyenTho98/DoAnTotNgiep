package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WardDTO {

    public String name;

    public String code;

    public DistrictDTO district;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

}
