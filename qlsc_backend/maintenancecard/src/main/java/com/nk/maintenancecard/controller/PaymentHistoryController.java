package com.nk.maintenancecard.controller;
import com.nk.maintenancecard.dto.MaintenanceCardDTO;
import com.nk.maintenancecard.dto.PaymentHistoryDTO;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.exception.maintenanceCardException.MoneyExceedException;
import com.nk.maintenancecard.model.PaymentHistoryByIdCustomer;
import com.nk.maintenancecard.service.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class PaymentHistoryController {

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @PostMapping("/paymentHistories")
    public ResponseEntity<MaintenanceCardDTO> insertPaymentHistory(@RequestBody List<PaymentHistoryDTO> paymentHistoryDTOs) throws NotFoundException, MoneyExceedException {
        MaintenanceCardDTO maintenanceCardDTO = paymentHistoryService.insertPaymentHistory(paymentHistoryDTOs);
        return new ResponseEntity(maintenanceCardDTO, HttpStatus.OK);
    }

    @GetMapping("/paymentHistories/customer")
    public ResponseEntity<Map<String,Object>> getPaymentHistoriesByIdCustomer(@ModelAttribute("paymentHistoryByIdCustomer") PaymentHistoryByIdCustomer paymentHistoryByIdCustomer){
        System.out.println(paymentHistoryByIdCustomer);
        Map<String,Object> allPaymentHistories = paymentHistoryService.getPaymentHistoryByIdCustomer(paymentHistoryByIdCustomer);
        return new ResponseEntity(allPaymentHistories, HttpStatus.OK);
    }

}
