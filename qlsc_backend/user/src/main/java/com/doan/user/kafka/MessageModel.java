package com.doan.user.kafka;

import com.doan.user.entity.MaintenanceCard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {

    private String maintenanceCard;
    private String maintenanceCardCode;
    private String author;
    private int type;
    private String repairmanEmail;
    private String coordinatorEmail;

}
