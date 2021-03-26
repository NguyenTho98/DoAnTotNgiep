package com.sapo.qlsc.dto;

import java.util.List;

public class PaymentMethodDTO extends BaseDTO{

    private String name;

    private List<PaymentHistoryDTO> paymentHistories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentHistoryDTO> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistoryDTO> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }
}
