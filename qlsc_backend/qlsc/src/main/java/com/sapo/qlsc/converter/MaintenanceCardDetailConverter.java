package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.MaintenanceCardDetailDTO;
import com.sapo.qlsc.entity.MaintenanceCardDetail;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceCardDetailConverter {

    public MaintenanceCardDetailDTO convertToDTO(MaintenanceCardDetail maintenanceCardDetail){
        MaintenanceCardDetailDTO maintenanceCardDetailDTO = new MaintenanceCardDetailDTO();
        maintenanceCardDetailDTO.setId(maintenanceCardDetail.getId());
        maintenanceCardDetailDTO.setStatus(maintenanceCardDetail.getStatus());
        return maintenanceCardDetailDTO;
    }

}
