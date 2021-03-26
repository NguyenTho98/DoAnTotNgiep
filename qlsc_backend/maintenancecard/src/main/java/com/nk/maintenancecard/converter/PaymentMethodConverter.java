package com.nk.maintenancecard.converter;

import com.nk.maintenancecard.dto.PaymentMethodDTO;
import com.nk.maintenancecard.entity.PaymentMethod;
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
