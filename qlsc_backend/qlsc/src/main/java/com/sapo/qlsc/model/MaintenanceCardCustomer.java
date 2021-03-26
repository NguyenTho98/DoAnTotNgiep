package com.sapo.qlsc.model;

import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

public class MaintenanceCardCustomer {

    final byte[] PAY_STATUS = {0,1};
    final byte[] WORK_STATUS = {0,1,2};
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

    public byte[] getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(byte[] workStatus) {
        this.workStatus = workStatus;
    }

    public byte[] getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(byte[] payStatus) {
        this.payStatus = payStatus;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "MaintenanceCardCustomer{" +
                "PAY_STATUS=" + Arrays.toString(PAY_STATUS) +
                ", WORK_STATUS=" + Arrays.toString(WORK_STATUS) +
                ", page=" + page +
                ", size=" + size +
                ", search='" + search + '\'' +
                ", nameField='" + nameField + '\'' +
                ", order='" + order + '\'' +
                ", payStatus=" + Arrays.toString(payStatus) +
                ", workStatus=" + Arrays.toString(workStatus) +
                ", id=" + id +
                '}';
    }
}
