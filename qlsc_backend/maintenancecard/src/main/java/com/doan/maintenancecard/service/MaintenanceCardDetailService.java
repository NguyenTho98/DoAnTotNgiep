package com.doan.maintenancecard.service;


import com.doan.maintenancecard.dto.MaintenanceCardDTO;
import com.doan.maintenancecard.exception.commonException.NotFoundException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotFoundRepairmanException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MaintenanceCardDetailService {

    public MaintenanceCardDTO updateStatusMaintenanceCardDetail(Long id, String email) throws NotFoundException, NotFoundRepairmanException, JsonProcessingException;

}
