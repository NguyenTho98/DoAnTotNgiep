package com.sapo.qlsc.model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

public class CustomerFilter {

    final byte[] PAY_STATUS = {0,1};
    private int page;
    private int size;
    private byte[] payStatus;

    public CustomerFilter() {
        this.page = 1;
        this.size = 5;
        this.payStatus = PAY_STATUS;
    }

    @ModelAttribute("CustomerFilter")
    public CustomerFilter customerFilter(){
        CustomerFilter customerFilter = new CustomerFilter();
        customerFilter.setPage(1);
        customerFilter.setSize(5);
        return customerFilter;
    }

    public byte[] getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(byte[] payStatus) {
        this.payStatus = payStatus;
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

    @Override
    public String toString() {
        return "CustomerFilter{" +
                "PAY_STATUS=" + Arrays.toString(PAY_STATUS) +
                ", page=" + page +
                ", size=" + size +
                ", payStatus=" + Arrays.toString(payStatus) +
                '}';
    }
}
