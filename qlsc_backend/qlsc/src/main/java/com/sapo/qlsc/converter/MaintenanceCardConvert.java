package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.DistrictDTO;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.entity.MaintenanceCard;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceCardConvert {

    public MaintenanceCardDTO convertToDTO(MaintenanceCard maintenanceCard){

        MaintenanceCardDTO maintenanceCardDTO = new MaintenanceCardDTO();
        maintenanceCardDTO.setId(maintenanceCard.getId());
        maintenanceCardDTO.setCode(maintenanceCard.getCode());
        maintenanceCardDTO.setCreatedDate(maintenanceCard.getCreatedDate());
        maintenanceCardDTO.setModifiedDate(maintenanceCard.getModifiedDate());
        maintenanceCardDTO.setPlatesNumber(maintenanceCard.getPlatesNumber());
        maintenanceCardDTO.setDescription(maintenanceCard.getDescription());
        maintenanceCardDTO.setReturnDate(maintenanceCard.getReturnDate());
        maintenanceCardDTO.setPrice(maintenanceCard.getPrice());
        maintenanceCardDTO.setWorkStatus(maintenanceCard.getWorkStatus());
        maintenanceCardDTO.setPayStatus(maintenanceCard.getPayStatus());
        maintenanceCardDTO.setModel(maintenanceCard.getModel());
        maintenanceCardDTO.setColor(maintenanceCard.getCode());

        return maintenanceCardDTO;
    }
}
