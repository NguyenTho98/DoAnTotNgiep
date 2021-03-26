package com.sapo.qlsc.repository;

import com.sapo.qlsc.dto.StatisticRepairmanDTO;
import com.sapo.qlsc.dto.TotalMoneyDTO;

import java.util.List;

public interface BusinessInformationCustom {

    int getTotalCustomer(String date);

    int getTotalMaintenanceCard(String date);

    int getTotalMaintenanceCardSuccess(String date);

    int getTotalMaintenanceCardSuccessNotPay(String date);

    int getTotalMaintenanceCardSuccessPayed(String date);
//    BigDecimal getTotalMoney(Date startDate, Date endDate);

    TotalMoneyDTO getMoneyDto(String date);

    List<StatisticRepairmanDTO> getTopRepairMan(String startDate, String endDate);

    List<StatisticRepairmanDTO> getTopService(String startDate, String endDate);

}
