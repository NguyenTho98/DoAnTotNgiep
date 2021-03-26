package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.*;
import com.sapo.qlsc.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WarrantyCardConverter {

    public MaintenanceCard convertToEntity(MaintenanceCardDTO warrantyCardDTO){
        ModelMapper modelMapper = new ModelMapper();
        MaintenanceCard warrantyCard = modelMapper.map(warrantyCardDTO, MaintenanceCard.class);
        return warrantyCard;
    }

    public MaintenanceCardDTO convertToDTO(MaintenanceCard warrantyCard){
        MaintenanceCardDTO warrantyCardDTO = new MaintenanceCardDTO();
//        warrantyCardDTO.setCode(warrantyCard.getCode());
//        warrantyCardDTO.setColor(warrantyCard.getColor());
//        User coordinator = warrantyCard.getCoordinator();
//        UserDTO coordinatorDTO = new UserDTO();
//        coordinatorDTO.setId(coordinator.getId());
//        coordinatorDTO.setFullName(coordinator.getFullName());
//        warrantyCardDTO.setCoordinator(coordinatorDTO);
//        Customer customer = warrantyCard.getCustomer();
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        warrantyCardDTO.setCustomer(customerDTO);
//        warrantyCardDTO.setDescription(warrantyCard.getDescription());
//        warrantyCardDTO.setModel(warrantyCard.getModel());
//        warrantyCardDTO.setPlatesNumber(warrantyCard.getPlatesNumber());
////        List<MaintenanceCardDetail> warrantyCardDetails = warrantyCard.getWarrantyCardDetails();
////        Long total = Long.valueOf(0);
////        for(MaintenanceCardDetail warrantyCardDetail : warrantyCardDetails){
////            total+= Long.valueOf(warrantyCardDetail.getPrice().longValue());
////        }
////        warrantyCardDTO.setPrice(BigDecimal.valueOf(total));
////        System.out.println(total);
//        User repairman = warrantyCard.getRepairman();
//        UserDTO repairmanDTO = new UserDTO();
//        repairmanDTO.setId(repairman.getId());
//        repairmanDTO.setFullName(repairman.getFullName());
//        warrantyCardDTO.setRepairman(repairmanDTO);
//        warrantyCardDTO.setReturnDate(warrantyCard.getReturnDate());
//        warrantyCardDTO.setId(warrantyCard.getId());
//        warrantyCardDTO.setWorkStatus(warrantyCard.getWorkStatus());
//        warrantyCardDTO.setPayStatus(warrantyCard.getPayStatus());
        return warrantyCardDTO;
    }

}
