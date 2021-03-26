package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.dto.PaymentHistoryDTO;
import com.sapo.qlsc.dto.PaymentMethodDTO;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.PaymentHistory;
import com.sapo.qlsc.entity.PaymentMethod;
import com.sapo.qlsc.repository.PaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentHistoryConverter {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private MaintenanceCardConvert maintenanceCardConvert;

    @Autowired
    private PaymentMethodConverter paymentMethodConverter;

    public PaymentHistory convertToEntity(PaymentHistoryDTO paymentHistoryDTO){
        ModelMapper modelmapper = new ModelMapper();
        return modelmapper.map(paymentHistoryDTO,PaymentHistory.class);
    }

    public PaymentHistoryDTO convertToDTO(PaymentHistory paymentHistory){
        PaymentHistoryDTO paymentHistoryDTO = new PaymentHistoryDTO();
        paymentHistoryDTO.setMoney(paymentHistory.getMoney());
        paymentHistoryDTO.setId(paymentHistory.getId());
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentHistory.getPaymentMethod().getId()).orElse(null);
        paymentMethodDTO.setId(paymentMethod.getId());
        paymentMethodDTO.setName(paymentMethod.getName());
        System.out.println(paymentMethod.getName());
        paymentHistoryDTO.setPaymentMethod(paymentMethodDTO);
        MaintenanceCardDTO maintenanceCardDTO = new MaintenanceCardDTO();
        MaintenanceCard maintenanceCard = paymentHistory.getMaintenanceCard();
        maintenanceCardDTO.setId(maintenanceCard.getId());
        paymentHistoryDTO.setMaintenanceCard(maintenanceCardDTO);
        paymentHistoryDTO.setCreatedDate(paymentHistory.getCreatedDate());
        return paymentHistoryDTO;
    }

    public PaymentHistoryDTO convertPaymentHistoryDTO(PaymentHistory paymentHistory){
        PaymentHistoryDTO paymentHistoryDTO = new PaymentHistoryDTO();
        paymentHistoryDTO.setId(paymentHistory.getId());
        paymentHistoryDTO.setCreatedDate(paymentHistory.getCreatedDate());
        paymentHistoryDTO.setModifiedDate(paymentHistory.getModifiedDate());
        paymentHistoryDTO.setMoney(paymentHistory.getMoney());
        if(paymentHistory.getMaintenanceCard() != null){
//            MaintenanceCardDTO maintenanceCardDTO = new MaintenanceCardDTO();
//            maintenanceCardDTO.setCode(paymentHistory.getMaintenanceCard().getCode());
//            paymentHistoryDTO.setMaintenanceCard(maintenanceCardDTO);
//            System.out.println(maintenanceCardDTO.getCustomer());
            paymentHistoryDTO.setMaintenanceCard(maintenanceCardConvert.convertToDTO(paymentHistory.getMaintenanceCard()));
        }
        if(paymentHistory.getPaymentMethod() != null){
            PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
            paymentMethodDTO.setId(paymentHistory.getPaymentMethod().getId());
            paymentMethodDTO.setName(paymentHistory.getPaymentMethod().getName());
            paymentHistoryDTO.setPaymentMethod(paymentMethodDTO);
        }
        //System.out.println(paymentHistory.getPaymentMethod());
        return paymentHistoryDTO;
    }

}
