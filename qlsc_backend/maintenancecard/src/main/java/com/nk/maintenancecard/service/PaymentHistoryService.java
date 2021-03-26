package com.nk.maintenancecard.service;


import com.nk.maintenancecard.dto.MaintenanceCardDTO;
import com.nk.maintenancecard.dto.PaymentHistoryDTO;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.exception.maintenanceCardException.MoneyExceedException;
import com.nk.maintenancecard.model.PaymentHistoryByIdCustomer;

import java.util.List;
import java.util.Map;

public interface PaymentHistoryService {

    public MaintenanceCardDTO insertPaymentHistory(List<PaymentHistoryDTO> paymentHistoryDTOs) throws NotFoundException, MoneyExceedException;

    Map<String, Object> getPaymentHistoryByIdCustomer(PaymentHistoryByIdCustomer paymentHistoryByIdCustomer);
}
