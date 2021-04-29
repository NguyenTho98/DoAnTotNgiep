package com.doan.maintenancecard.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.ModelAttribute;

@Setter
@Getter
@ToString
public class MaintenanceCardCustomer {

    final byte[] PAY_STATUS = {0, 1};
    final byte[] WORK_STATUS = {0, 1, 2};
    private int page;
    private int size;
    private String search;
    private String nameField;
    private String order;
    private byte[] payStatus;
    private byte[] workStatus;

    @NotNull
    private Long id;

    public MaintenanceCardCustomer() {
        this.size = 5;
        this.page = 1;
        this.search = "";
        this.id = 0L;
        this.nameField = "";
        this.order = "";
        this.payStatus = PAY_STATUS;
        this.workStatus = WORK_STATUS;
    }

    @ModelAttribute("maintenanceCardCustomer")
    public MaintenanceCardCustomer getMaintenanceCardCustomer() {
        MaintenanceCardCustomer maintenanceCardCustomer = new MaintenanceCardCustomer();
        maintenanceCardCustomer.setPage(1);
        maintenanceCardCustomer.setSize(5);
        maintenanceCardCustomer.setSearch("");
        maintenanceCardCustomer.setNameField("");
        maintenanceCardCustomer.setOrder("");
        return maintenanceCardCustomer;
    }
}
