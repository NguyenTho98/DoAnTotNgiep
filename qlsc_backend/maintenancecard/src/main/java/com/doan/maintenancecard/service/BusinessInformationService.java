package com.doan.maintenancecard.service;

import com.doan.maintenancecard.model.BusinessResponse;

import java.math.BigDecimal;
import java.text.ParseException;

public interface BusinessInformationService {

    int getTotalMaintenanceCard();

    int getTotalMaintenanceCardSuccess();

    int getTotalMaintenanceCardSuccessNotPay();

    int getTotalMaintenanceCardSuccessPayed();

    int getTotalMaintenanceCards(String startDate, String endDate) throws ParseException;

    BigDecimal getTotalMoney(String startDate, String endDate) throws ParseException;

    BigDecimal getTotalLiabilities(String startDate, String endDate) throws ParseException;

    BusinessResponse getReport(String from, String to);

}
