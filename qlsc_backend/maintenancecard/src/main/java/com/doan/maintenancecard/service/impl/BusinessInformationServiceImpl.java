package com.doan.maintenancecard.service.impl;

import com.doan.maintenancecard.model.BusinessResponse;
import com.doan.maintenancecard.model.TotalMoney;
import com.doan.maintenancecard.repository.BusinessInformationCustom;
import com.doan.maintenancecard.repository.MaintenanceCardRepository;
import com.doan.maintenancecard.service.BusinessInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BusinessInformationServiceImpl implements BusinessInformationService {

    private final BusinessInformationCustom businessInformationCustom;
    private final MaintenanceCardRepository maintenanceCardRepository;

    @Override
    public int getTotalMaintenanceCard() {
        String date = getDateNow();
        return businessInformationCustom.getTotalMaintenanceCard(date);
    }

    @Override
    public int getTotalMaintenanceCardSuccess() {
        String date = getDateNow();
        return businessInformationCustom.getTotalMaintenanceCardSuccess(date);
    }

    @Override
    public int getTotalMaintenanceCardSuccessNotPay() {
        String date = getDateNow();
        return businessInformationCustom.getTotalMaintenanceCardSuccessNotPay(date);
    }

    @Override
    public int getTotalMaintenanceCardSuccessPayed() {
        String date = getDateNow();
        return businessInformationCustom.getTotalMaintenanceCardSuccessPayed(date);
    }

    @Override
    public int getTotalMaintenanceCards(String startDate, String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String strSDate = startDate;
        String strEDate = endDate;
        Date sDate = formatter.parse(startDate);
        Date eDate = formatter.parse(strEDate);
        Date eDate1 = new Date(eDate.getTime() + (1000 * 60 * 60 * 24));
        return maintenanceCardRepository.getTotalMaintenanceCard(sDate, eDate1);
    }

    @Override
    public BigDecimal getTotalMoney(String startDate, String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String strSDate = startDate;
        String strEDate = endDate;
        Date sDate = formatter.parse(startDate);
        Date eDate = formatter.parse(strEDate);
        Date eDate1 = new Date(eDate.getTime() + (1000 * 60 * 60 * 24));
        BigDecimal bigDecimal = maintenanceCardRepository.getTotalMoney(sDate, eDate1);
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.valueOf(0);
        }
        return bigDecimal;
    }

    @Override
    public BigDecimal getTotalLiabilities(String startDate, String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String strSDate = startDate;
        String strEDate = endDate;
        Date sDate = formatter.parse(startDate);
        Date eDate = formatter.parse(strEDate);
        Date eDate1 = new Date(eDate.getTime() + (1000 * 60 * 60 * 24));
        //System.out.println(eDate1);
        BigDecimal bigDecimal = maintenanceCardRepository.getTotalLiabilities(sDate, eDate1);
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.valueOf(0);
        }
        return bigDecimal;
    }

    @Override
    public BusinessResponse getReport(String from, String to) {
        BusinessResponse businessResponse = new BusinessResponse();
        try {
            Date eDatetime = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(to).getTime() + (1000 * 60 * 60 * 24));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = dateFormat.format(new SimpleDateFormat("dd/MM/yyyy").parse(from));
            String eDate = dateFormat.format(eDatetime);
            businessResponse.setTopStaffs(businessInformationCustom.getTopRepairMan(sDate, eDate));
            businessResponse.setTopServices(businessInformationCustom.getTopService(sDate, eDate));
            businessResponse.setTotalMonies(getTotalMonies(from, to));
        } catch (Exception e) {
            return businessResponse;
        }
        return businessResponse;
    }

    private String getDateNow() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
                if (totalMoney.getDate() == null) {
                    totalMoney.setDate(date);
                }
                if (totalMoney.getTotalDayMoney() == null) {
                    totalMoney.setTotalDayMoney(BigDecimal.valueOf(0));
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
