package com.sapo.qlsc.service;

import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.dto.PaymentHistoryDTO;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.exception.maintenanceCardException.MoneyExceedException;
import com.sapo.qlsc.model.PaymentHistoryByIdCustomer;
import com.sapo.qlsc.exception.commonException.NotFoundException;

import java.util.List;
import java.util.Map;

public interface PaymentHistoryService {

    public MaintenanceCardDTO insertPaymentHistory(List<PaymentHistoryDTO> paymentHistoryDTOs) throws NotFoundException, MoneyExceedException;

    Map<String, Object> getPaymentHistoryByIdCustomer(PaymentHistoryByIdCustomer paymentHistoryByIdCustomer);
}
