package com.doan.maintenancecard.controller;

import com.doan.maintenancecard.dto.BusinessInformationDTO;
import com.doan.maintenancecard.model.BusinessResponse;
import com.doan.maintenancecard.model.FilterReport;
import com.doan.maintenancecard.service.BusinessInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("admin")
@RequiredArgsConstructor
public class BusinessInformationController {

    private final BusinessInformationService businessInformationService;

    @GetMapping("totals")
    public ResponseEntity<BusinessInformationDTO> getTotals(
        @RequestParam(name = "startDate", defaultValue = "") String startDate,
        @RequestParam(name = "endDate", defaultValue = "") String endDate) throws ParseException {
        BusinessInformationDTO businessInformationDTO = new BusinessInformationDTO();
        businessInformationDTO.setTotalMaintenanceCard(businessInformationService.getTotalMaintenanceCard());
        businessInformationDTO.setTotalMaintenanceCardSuccess(businessInformationService.getTotalMaintenanceCardSuccess());
        businessInformationDTO.setTotalMoney(businessInformationService.getTotalMoney(startDate, endDate));
        businessInformationDTO.setTotalMaintenanceCards(businessInformationService.getTotalMaintenanceCards(startDate, endDate));
        businessInformationDTO.setTotalMaintenanceCardScNotPay(businessInformationService.getTotalMaintenanceCardSuccessNotPay());
        businessInformationDTO.setTotalMaintenanceCardScPayed(businessInformationService.getTotalMaintenanceCardSuccessPayed());
        businessInformationDTO.setTotalLiabilities((businessInformationService.getTotalLiabilities(startDate, endDate)));
        return new ResponseEntity<>(businessInformationDTO, HttpStatus.OK);
    }

    @GetMapping("business/report")
    public BusinessResponse getReport(@Valid FilterReport filterReport) {
        return businessInformationService.getReport(filterReport.getFrom(), filterReport.getTo());
    }

}
