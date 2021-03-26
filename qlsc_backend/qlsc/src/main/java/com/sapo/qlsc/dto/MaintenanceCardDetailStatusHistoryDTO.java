package com.sapo.qlsc.dto;

import com.sapo.qlsc.entity.MaintenanceCardDetail;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceCardDetailStatusHistoryDTO extends BaseDTO{

    private byte status;

    private String name;

}
