package com.nk.maintenancecard.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nk.maintenancecard.dto.MaintenanceCardDTO;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotFoundRepairmanException;

public interface MaintenanceCardDetailService {

    public MaintenanceCardDTO updateStatusMaintenanceCardDetail(Long id, String email) throws NotFoundException, NotFoundRepairmanException, JsonProcessingException;

}
