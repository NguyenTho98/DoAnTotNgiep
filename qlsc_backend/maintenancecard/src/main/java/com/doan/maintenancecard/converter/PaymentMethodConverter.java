package com.doan.maintenancecard.converter;

import com.doan.maintenancecard.dto.PaymentMethodDTO;
import com.doan.maintenancecard.entity.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodConverter {

    public PaymentMethodDTO convertToDTO(PaymentMethod paymentMethod){
        ModelMapper modelMapper = new ModelMapper();
        PaymentMethodDTO paymentMethodDTO = modelMapper.map(paymentMethod, PaymentMethodDTO.class);
        return paymentMethodDTO;
    }

}
