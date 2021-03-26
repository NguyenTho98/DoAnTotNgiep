package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.*;
import com.sapo.qlsc.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {
//    private final MaintenanceCardConverter maintenanceCardConverter;
//    private final MaintenanceCardConvert maintenanceCardConvert;
//
//    private final WardConverter wardConverter;
//
//    public CustomerConverter(MaintenanceCardConverter maintenanceCardConverter, MaintenanceCardConvert maintenanceCardConvert, WardConverter wardConverter) {
//        this.maintenanceCardConverter = maintenanceCardConverter;
//        this.maintenanceCardConvert = maintenanceCardConvert;
//        this.wardConverter = wardConverter;
//    }
//    }

    @Autowired
    private MaintenanceCardConverter maintenanceCardConverter;
    @Autowired
    private MaintenanceCardConvert maintenanceCardConvert;
    @Autowired
    private WardConverter wardConverter;

    public CustomerDTO convertToDTO(Customer customer, String check){
        CustomerDTO customerDTO = new CustomerDTO();
        boolean paid = false;
        int totalNotPay = 0;
        customerDTO.setId(customer.getId());
        customerDTO.setCode(customer.getCode());
        customerDTO.setCreatedDate(customer.getCreatedDate());
        customerDTO.setModifiedDate(customer.getModifiedDate());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setDescription(customer.getDescription());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setStatus(customer.getStatus());

        if(customer.getWard() != null){
            customerDTO.setWard(wardConverter.convertToDTO(customer.getWard()));
        }

        if(customer.getMaintenanceCards() != null){
            List<MaintenanceCard> maintenanceCards = customer.getMaintenanceCards();
            List<MaintenanceCardDTO> maintenanceCardDTOS = new ArrayList<>();
            for (MaintenanceCard maintenanceCard : maintenanceCards){
                maintenanceCardDTOS.add(maintenanceCardConvert.convertToDTO(maintenanceCard));
                if (maintenanceCard.getPayStatus() == 0){
                    paid = true;
                    totalNotPay += 1;
                }
            }
            customerDTO.setMaintenanceCards(maintenanceCardDTOS);
        }
        System.out.println("sd"+totalNotPay);

        if(check == "get"){
            if(customer.getMaintenanceCards() != null &&customer.getMaintenanceCards().size() == 0){
                customerDTO.setPay_status("2");
            } else if(paid){
                customerDTO.setPay_status("0");
            } else{
                customerDTO.setPay_status("1");
            }

            if(customerDTO.getPay_status().equals("0")){
                List<MaintenanceCard> maintenanceCards = customer.getMaintenanceCards();
                Long total = Long.valueOf(0);
                for (MaintenanceCard maintenanceCard : maintenanceCards){
                    if (maintenanceCard.getPayStatus() == 0){
                        total += Long.valueOf(maintenanceCard.getPrice().longValue());
                    }
                }
                customerDTO.setCurrent_debt(BigDecimal.valueOf(total));
            }else{
                customerDTO.setCurrent_debt(BigDecimal.valueOf(0));
            }
            customerDTO.setTotalNotPay(totalNotPay);

        }

        return customerDTO;
    }

    public Customer convertToEntity(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customerEntity = modelMapper.map(customerDTO, Customer.class);
        return customerEntity;
    }

    public CustomerDTO convertCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setCode(customer.getCode());
        customerDTO.setDescription(customer.getDescription());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setStatus(customer.getStatus());
        customerDTO.setCreatedDate(customer.getCreatedDate());
        customerDTO.setModifiedDate(customer.getModifiedDate());
        return customerDTO;
    }

}
