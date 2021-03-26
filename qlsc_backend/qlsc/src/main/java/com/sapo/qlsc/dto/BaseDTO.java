package com.sapo.qlsc.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {

    private Long id;
    private Date createdDate;
    private Date modifiedDate;
}
