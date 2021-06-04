package com.doan.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceCardDetailStatusHistoryDTO extends BaseDTO {

    private byte status;
    private String name;

}
