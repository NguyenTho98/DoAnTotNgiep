package com.doan.user.dto;

import java.util.Date;

public class BaseDTO {

    private Long id;
    private Date createdDate;
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate = new Date();
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = new Date();
    }
}
