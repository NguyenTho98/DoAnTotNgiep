package com.doan.maintenancecard.service.impl;

import com.doan.maintenancecard.model.BusinessResponse;
import com.doan.maintenancecard.model.BusinessToday;
import com.doan.maintenancecard.model.TotalMoney;
import com.doan.maintenancecard.repository.BusinessInformationCustom;
import com.doan.maintenancecard.service.BusinessInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessInformationServiceImpl implements BusinessInformationService {

    private final BusinessInformationCustom businessInformationCustom;
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public BusinessResponse getReport(String from, String to) {
        BusinessResponse businessResponse = new BusinessResponse();
        try {
            Date eDatetime = new Date(new SimpleDateFormat(DATE_FORMAT).parse(to).getTime() + (1000 * 60 * 60 * 24));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = dateFormat.format(new SimpleDateFormat(DATE_FORMAT).parse(from));
            String eDate = dateFormat.format(eDatetime);
            businessResponse.setTopStaffs(businessInformationCustom.getTopRepairMan(sDate, eDate));
            businessResponse.setTopServices(businessInformationCustom.getTopService(sDate, eDate));
            businessResponse.setTotalMonies(getTotalMonies(from, to));
            String dateNow = getDateNow();
            BusinessToday businessToday = new BusinessToday();
            businessToday.setTotalMaintenanceCard(businessInformationCustom.getTotalMaintenanceCard(dateNow));
            businessToday.setTotalMaintenanceCardSuccess(businessInformationCustom.getTotalMaintenanceCardSuccess(dateNow));
            businessToday.setTotalMaintenanceCardScPayed(businessInformationCustom.getTotalMaintenanceCardSuccessPayed(dateNow));
            businessToday.setTotalMaintenanceCardScNotPay(businessInformationCustom.getTotalMaintenanceCardSuccessNotPay(dateNow));
            businessResponse.setBusinessToday(businessToday);
        } catch (Exception e) {
            return businessResponse;
        }
        return businessResponse;
    }

    private String getDateNow() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }

    private List<TotalMoney> getTotalMonies(String from, String to) {
        int startDay = Integer.parseInt(from.substring(0, 2));
        int endDay = Integer.parseInt(to.substring(0, 2));
        int startMonth = Integer.parseInt(from.substring(3, 5));
        int endMonth = Integer.parseInt(to.substring(3, 5));
        List<String> dates = getDates(startDay, endDay, startMonth, endMonth, from, to);
        List<TotalMoney> monies = new ArrayList<>();
        try {
            dates.forEach(date -> {
                TotalMoney totalMoney = businessInformationCustom.getMoney(date);
                if (totalMoney.getTime() == null) {
                    totalMoney.setTime(date);
                }
                if (totalMoney.getTotal() == null) {
                    totalMoney.setTotal(BigDecimal.valueOf(0));
                }
                monies.add(totalMoney);
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return monies;
    }

    private List<String> getDates(int startDay, int endDay,
                                  int startMonth, int endMonth,
                                  String from, String to) {
        List<String> dates = new ArrayList<>();
        if (endMonth - startMonth == 0) {
            for (int i = startDay; i <= endDay; i++) {
                if (i < 10) {
                    dates.add("0" + i + from.substring(2));
                } else {
                    dates.add(i + from.substring(2));
                }
            }
        } else if (endMonth - startMonth == 1) {
            for (int i = startDay; i <= 31; i++) {
                if (i < 10) {
                    dates.add("0" + i + from.substring(2));
                } else {
                    dates.add(i + from.substring(2));
                }
            }
            for (int i = 1; i <= endDay; i++) {
                if (i < 10) {
                    dates.add("0" + i + to.substring(2));
                } else {
                    dates.add(i + to.substring(2));
                }
            }
        }
        return dates;
    }

}
