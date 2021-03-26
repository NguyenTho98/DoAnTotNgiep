package com.sapo.qlsc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.exception.CodeExistedException;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.exception.commonException.UnknownException;
import com.sapo.qlsc.exception.maintenanceCardException.NotEnoughProductException;
import com.sapo.qlsc.exception.maintenanceCardException.NotFoundRepairmanException;
import com.sapo.qlsc.exception.maintenanceCardException.NotUpdateException;
import com.sapo.qlsc.model.MaintenanceCardCustomer;
import com.sapo.qlsc.model.MaintenanceCardFilter;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MaintenanceCardService {

     MaintenanceCardDTO insertMaintenanceCard(MaintenanceCardDTO maintenanceCardDTO) throws NotEnoughProductException, CodeExistedException, JsonProcessingException;
     Map<String, Object> searchMaintenanceCard(MaintenanceCardFilter maintenanceCardFilter,String email,int role);
     MaintenanceCardDTO getMaintenanceCardById(Long id,String email,int role) throws NotFoundException;
     MaintenanceCardDTO updateMaintenanceCard(MaintenanceCardDTO maintenanceCardDTO,String email,int role) throws NotEnoughProductException, NotFoundException, CodeExistedException, NotUpdateException, UnknownException;
     Map<String, Object> getMaintenanceCardByIdCustomer(MaintenanceCardCustomer maintenanceCardCustomer);
     MaintenanceCardDTO updateAllStatusMaintenanceCard(Long id,String email,int role) throws NotFoundException, NotFoundRepairmanException;
     boolean deleteMaintenanceCard(Long id) throws NotFoundException, NotFoundRepairmanException, NotEnoughProductException, UnknownException;
     Map<String,Object> getMaintenanceCardByRepairMan(int PageNum, int PageSize, String sortBy, boolean descending,Long userId,String code,byte[] payStatus,byte[] workStatus);
     public List<String> getPlatesNumberByCustomerId(Long id);

}
