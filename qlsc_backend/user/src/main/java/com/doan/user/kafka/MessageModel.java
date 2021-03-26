package com.doan.user.kafka;

public class MessageModel {

    private String maintenanceCardCode;

    private String author;

    private int type;

    private String repairmanEmail;

    private String coordinatorEmail;

    public String getRepairmanEmail() {
        return repairmanEmail;
    }

    public void setRepairmanEmail(String repairmanEmail) {
        this.repairmanEmail = repairmanEmail;
    }

    public String getCoordinatorEmail() {
        return coordinatorEmail;
    }

    public void setCoordinatorEmail(String coordinatorEmail) {
        this.coordinatorEmail = coordinatorEmail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMaintenanceCardCode() {
        return maintenanceCardCode;
    }

    public void setMaintenanceCardCode(String maintenanceCardCode) {
        this.maintenanceCardCode = maintenanceCardCode;
    }
}
