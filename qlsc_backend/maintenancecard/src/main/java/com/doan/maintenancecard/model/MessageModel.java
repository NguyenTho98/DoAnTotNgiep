package com.doan.maintenancecard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageModel {

    private String maintenanceCardCode;
    private String author;
    private int type;
    private String repairmanEmail;
    private String coordinatorEmail;
}
