package com.doan.user.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodDTO extends BaseDTO{

    private String name;
    private List<PaymentHistoryDTO> paymentHistories;

}
