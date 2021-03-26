package com.sapo.qlsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.PaymentMethod;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentHistoryDTO extends BaseDTO{

    private MaintenanceCardDTO maintenanceCard;

    private PaymentMethodDTO paymentMethod;

    private BigDecimal money;

}
