package com.sapo.qlsc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provinces")
public class Province {

    @Column(name = "name", length = 50)
    public String name;

    @Id
    @Column(name = "code_province", length = 50)
    public String code;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "province")
    public List<District> districts;

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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
