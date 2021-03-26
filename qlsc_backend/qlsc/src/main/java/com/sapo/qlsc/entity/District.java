package com.sapo.qlsc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "districts")
public class District {

    @Column(name = "name", length = 50)
    public String name;

    @Id
    @Column(name = "code_district", length = 50)
    public String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_province")
    public Province province;

    @OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
    public List<Ward> wards;

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
