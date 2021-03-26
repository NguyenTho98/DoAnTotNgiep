package com.sapo.qlsc.service;

import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.dto.MaintenanceCardDetailDTO;
import com.sapo.qlsc.entity.MaintenanceCardDetail;
import com.sapo.qlsc.exception.CodeExistedException;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.exception.maintenanceCardException.NotEnoughProductException;
import com.sapo.qlsc.exception.maintenanceCardException.NotFoundRepairmanException;

public interface MaintenanceCardDetailService {

    public MaintenanceCardDTO updateStatusMaintenanceCardDetail(Long id,String email) throws NotFoundException, NotFoundRepairmanException;

}
