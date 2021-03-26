package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.PaymentMethodDTO;
import com.sapo.qlsc.dto.WardDTO;
import com.sapo.qlsc.entity.PaymentMethod;
import com.sapo.qlsc.entity.Ward;
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
