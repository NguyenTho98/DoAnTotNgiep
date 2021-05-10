package com.doan.maintenancecard.repository;

import com.doan.maintenancecard.model.StatisticRepairman;
import com.doan.maintenancecard.model.TotalMoney;

import java.util.List;

public interface BusinessInformationCustom {

    int getTotalMaintenanceCard(String date);

    int getTotalMaintenanceCardSuccess(String date);

    int getTotalMaintenanceCardSuccessNotPay(String date);

    int getTotalMaintenanceCardSuccessPayed(String date);

    TotalMoney getMoney(String date);

    List<StatisticRepairman> getTopService(String startDate, String endDate);

    List<StatisticRepairman> getTopRepairMan(String startDate, String endDate);

}
