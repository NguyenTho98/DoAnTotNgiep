package com.sapo.qlsc.model;

import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

public class PaymentHistoryByIdCustomer {

    final Long[] PAY_METHODS = {1L,2L};
    private int page;
    private int size;

    @NotNull
    private Long id;

    private String search;
    private Long[] payMethods;

    public PaymentHistoryByIdCustomer() {
        this.size = 5;
        this.page = 1;
        this.id = 0L;
        this.search = "";
        this.payMethods = PAY_METHODS;
    }

    @ModelAttribute("paymentHistoryByIdCustomer")
    public PaymentHistoryByIdCustomer getPaymentHistoryByIdCustomer() {
        PaymentHistoryByIdCustomer paymentHistoryByIdCustomer = new PaymentHistoryByIdCustomer();
        paymentHistoryByIdCustomer.setPage(1);
        paymentHistoryByIdCustomer.setSize(5);
        paymentHistoryByIdCustomer.setSearch("");
        return paymentHistoryByIdCustomer;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Long[] getPayMethods() {
        return payMethods;
    }

    public void setPayMethods(Long[] payMethods) {
        this.payMethods = payMethods;
    }

    @Override
    public String toString() {
        return "PaymentHistoryByIdCustomer{" +
                "PAY_METHODS=" + Arrays.toString(PAY_METHODS) +
                ", page=" + page +
                ", size=" + size +
                ", id=" + id +
                ", search='" + search + '\'' +
                ", payMethods=" + Arrays.toString(payMethods) +
                '}';
    }
}
