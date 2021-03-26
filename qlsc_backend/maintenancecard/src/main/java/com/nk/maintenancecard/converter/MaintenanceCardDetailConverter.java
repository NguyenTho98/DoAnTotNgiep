package com.nk.maintenancecard.converter;

import com.nk.maintenancecard.dto.MaintenanceCardDetailDTO;
import com.nk.maintenancecard.entity.MaintenanceCardDetail;
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
