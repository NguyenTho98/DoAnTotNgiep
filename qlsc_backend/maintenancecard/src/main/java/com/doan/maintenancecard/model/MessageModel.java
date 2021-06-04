package com.doan.maintenancecard.model;

import com.doan.maintenancecard.entity.MaintenanceCard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageModel {

    private String maintenanceCard;
    private String maintenanceCardCode;
    private String author;
    private int type;
    private String repairmanEmail;
    private String coordinatorEmail;
}
