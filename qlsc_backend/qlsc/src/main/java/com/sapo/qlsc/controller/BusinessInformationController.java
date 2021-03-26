package com.sapo.qlsc.controller;

import com.sapo.qlsc.dto.BusinessInformationDTO;
import com.sapo.qlsc.dto.StatisticRepairmanDTO;
import com.sapo.qlsc.dto.TotalMoneyDTO;
import com.sapo.qlsc.service.BusinessInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin/")
public class BusinessInformationController {

    private final BusinessInformationService businessInformationService;

    public BusinessInformationController(BusinessInformationService businessInformationService) {
        this.businessInformationService = businessInformationService;
    }

    @GetMapping("/totals")
    public ResponseEntity<BusinessInformationDTO> getTotals(
            @RequestParam(name = "startDate", required = true, defaultValue = "") String startDate,
            @RequestParam(name = "endDate", required = true, defaultValue = "") String endDate) throws ParseException {
        BusinessInformationDTO businessInformationDTO = new BusinessInformationDTO();
        businessInformationDTO.setTotalCustomer(businessInformationService.getTotalCustomer());
        businessInformationDTO.setTotalMaintenanceCard(businessInformationService.getTotalMaintenanceCard());
        businessInformationDTO.setTotalMaintenanceCardSuccess(businessInformationService.getTotalMaintenanceCardSuccess());
        businessInformationDTO.setTotalMoney(businessInformationService.getTotalMoney(startDate, endDate));
        businessInformationDTO.setTotalMaintenanceCards(businessInformationService.getTotalMaintenanceCards(startDate, endDate));
        businessInformationDTO.setTotalMaintenanceCardScNotPay(businessInformationService.getTotalMaintenanceCardSuccessNotPay());
        businessInformationDTO.setTotalMaintenanceCardScPayed(businessInformationService.getTotalMaintenanceCardSuccessPayed());
        businessInformationDTO.setTotalLiabilities((businessInformationService.getTotalLiabilities(startDate, endDate)));
        return new ResponseEntity<>(businessInformationDTO, HttpStatus.OK);
    }

    @GetMapping("/totalDayMoneys")
    public ResponseEntity<List<TotalMoneyDTO>> getTotalDayMoney(
            @RequestParam(name = "startDate", required = true, defaultValue = "") String startDate,
            @RequestParam(name = "endDate", required = true, defaultValue = "") String endDate) {
        List<TotalMoneyDTO> moneyDTOList = businessInformationService.getAllTotalMoney(startDate, endDate);
        return new ResponseEntity<>(moneyDTOList, HttpStatus.OK);
    }

    @GetMapping("/topRepairMans")
    public ResponseEntity<List<StatisticRepairmanDTO>> getTopRepairman(
            @RequestParam(name = "startDate", required = true, defaultValue = "") String startDate,
            @RequestParam(name = "endDate", required = true, defaultValue = "") String endDate) throws ParseException {
        Date sDate=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date eDate=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        List<StatisticRepairmanDTO> statisticRepairmanDTOS = businessInformationService.getTopRepairman(sDate, eDate);
        return new ResponseEntity<>(statisticRepairmanDTOS, HttpStatus.OK);
    }

    @GetMapping("/topServices")
    public ResponseEntity<List<StatisticRepairmanDTO>> getTopService(
            @RequestParam(name = "startDate", required = true, defaultValue = "") String startDate,
            @RequestParam(name = "endDate", required = true, defaultValue = "") String endDate) throws ParseException {
        Date sDate=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date eDate=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        List<StatisticRepairmanDTO> statisticRepairmanDTOS = businessInformationService.getTopService(sDate, eDate);
        return new ResponseEntity<>(statisticRepairmanDTOS, HttpStatus.OK);
    }

}
