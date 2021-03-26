package com.nk.maintenancecard.repository;

import com.nk.maintenancecard.dto.StatisticRepairmanDTO;
import com.nk.maintenancecard.dto.TotalMoneyDTO;

import java.util.List;

public interface BusinessInformationCustom {

    int getTotalMaintenanceCard(String date);

    int getTotalMaintenanceCardSuccess(String date);

    int getTotalMaintenanceCardSuccessNotPay(String date);

    int getTotalMaintenanceCardSuccessPayed(String date);
//    BigDecimal getTotalMoney(Date startDate, Date endDate);

    TotalMoneyDTO getMoneyDto(String date);

    List<StatisticRepairmanDTO> getTopService(String startDate, String endDate);

    List<StatisticRepairmanDTO> getTopRepairMan(String startDate, String endDate);

}
