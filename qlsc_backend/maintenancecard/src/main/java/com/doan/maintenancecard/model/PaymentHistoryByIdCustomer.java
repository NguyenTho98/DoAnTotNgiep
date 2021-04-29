package com.doan.maintenancecard.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

@Setter
@Getter
@ToString
public class PaymentHistoryByIdCustomer {

    final Long[] PAY_METHODS = {1L, 2L};
    private int page;
    private int size;

    @NotNull
    private Long id;

    private String search;
    private Long[] payMethods;

    public PaymentHistoryByIdCustomer() {
        this.size = 10;
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
}
