package com.doan.maintenancecard.converter;

import com.doan.maintenancecard.dto.MaintenanceCardDTO;
import com.doan.maintenancecard.dto.PaymentHistoryDTO;
import com.doan.maintenancecard.dto.PaymentMethodDTO;
import com.doan.maintenancecard.entity.MaintenanceCard;
import com.doan.maintenancecard.entity.PaymentHistory;
import com.doan.maintenancecard.entity.PaymentMethod;
import com.doan.maintenancecard.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentHistoryConverter {
    private final PaymentMethodRepository paymentMethodRepository;
    private final MaintenanceCardConverter maintenanceCardConvert;
    private final PaymentMethodConverter paymentMethodConverter;

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
