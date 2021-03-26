package com.doan.maintenancecard.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentHistoryDTO extends BaseDTO {

    private MaintenanceCardDTO maintenanceCard;

    private PaymentMethodDTO paymentMethod;

    private BigDecimal money;
}
