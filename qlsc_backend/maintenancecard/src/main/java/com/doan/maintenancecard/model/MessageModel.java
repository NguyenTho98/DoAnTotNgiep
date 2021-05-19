package com.doan.maintenancecard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageModel {

    private Long id;
    private Long repairmanId;
    private String maintenanceCardCode;
    private String author;
    private int type;
    private String url;
    private String content;
    private String title;
    private String repairmanEmail;
    private String coordinatorEmail;
}
