package com.sapo.qlsc.controller;

import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.dto.PaymentHistoryDTO;
import com.sapo.qlsc.entity.PaymentHistory;
import com.sapo.qlsc.exception.maintenanceCardException.MoneyExceedException;
import com.sapo.qlsc.model.MaintenanceCardCustomer;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.model.PaymentHistoryByIdCustomer;
import com.sapo.qlsc.service.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
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
