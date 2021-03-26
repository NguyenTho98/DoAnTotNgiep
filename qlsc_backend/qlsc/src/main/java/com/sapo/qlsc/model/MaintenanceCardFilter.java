package com.sapo.qlsc.model;

import com.sapo.qlsc.entity.MaintenanceCard;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MaintenanceCardFilter {

    final byte[] PAY_STATUS = {0,1};
    final byte[] WORK_STATUS = {0,1,2};
    private int page;
    private int size;
    private String search;
    private String nameField;
    private String order;
    private byte[] payStatus;
    private byte[] workStatus;


    public MaintenanceCardFilter() {
        this.size = 5;
        this.page = 1;
        this.search = "";
        this.nameField = "";
        this.order = "";
        this.payStatus = PAY_STATUS;
        this.workStatus = WORK_STATUS;
    }

    @ModelAttribute("maintenanceCardFilter")
    public MaintenanceCardFilter getMaintenanceCardFilter() {
        MaintenanceCardFilter maintenanceCardFilter = new MaintenanceCardFilter();
        maintenanceCardFilter.setPage(1);
        maintenanceCardFilter.setNameField("");
        maintenanceCardFilter.setOrder("");
        maintenanceCardFilter.setSearch("");
        maintenanceCardFilter.setSize(5);
        return maintenanceCardFilter;
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

    public byte[] getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(byte[] payStatus) {
        this.payStatus = payStatus;
    }

    public byte[] getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(byte[] workStatus) {
        this.workStatus = workStatus;
    }

    @Override
    public String toString() {
        return "MaintenanceCardFilter{" +
                "PAY_STATUS=" + Arrays.toString(PAY_STATUS) +
                ", WORK_STATUS=" + Arrays.toString(WORK_STATUS) +
                ", page=" + page +
                ", size=" + size +
                ", search='" + search + '\'' +
                ", nameField='" + nameField + '\'' +
                ", order='" + order + '\'' +
                ", payStatus=" + Arrays.toString(payStatus) +
                ", workStatus=" + Arrays.toString(workStatus) +
                '}';
    }
}
