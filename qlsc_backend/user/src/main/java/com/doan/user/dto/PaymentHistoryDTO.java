package com.doan.user.dto;

import com.doan.user.entity.MaintenanceCard;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentHistoryDTO extends BaseDTO {

    private MaintenanceCard maintenanceCard;
    private PaymentMethodDTO paymentMethod;
    private BigDecimal money;
}

