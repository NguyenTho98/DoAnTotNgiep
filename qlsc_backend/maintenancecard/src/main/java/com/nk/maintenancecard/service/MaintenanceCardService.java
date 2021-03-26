package com.nk.maintenancecard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nk.maintenancecard.dto.MaintenanceCardDTO;
import com.nk.maintenancecard.entity.MaintenanceCard;
import com.nk.maintenancecard.exception.CodeExistedException;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.exception.commonException.UnknownException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotEnoughProductException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotFoundRepairmanException;
import com.nk.maintenancecard.exception.maintenanceCardException.NotUpdateException;
import com.nk.maintenancecard.model.MaintenanceCardCustomer;
import com.nk.maintenancecard.model.MaintenanceCardFilter;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MaintenanceCardService {

     MaintenanceCardDTO insertMaintenanceCard(MaintenanceCardDTO maintenanceCardDTO) throws NotEnoughProductException, CodeExistedException, JsonProcessingException;
     Map<String, Object> searchMaintenanceCard(MaintenanceCardFilter maintenanceCardFilter, String email, int role);
     MaintenanceCardDTO getMaintenanceCardById(Long id,String email,int role) throws NotFoundException;
     MaintenanceCardDTO updateMaintenanceCard(MaintenanceCardDTO maintenanceCardDTO,String email,int role) throws NotEnoughProductException, NotFoundException, CodeExistedException, NotUpdateException, UnknownException, JsonProcessingException;
     Map<String, Object> getMaintenanceCardByIdCustomer(MaintenanceCardCustomer maintenanceCardCustomer);
     MaintenanceCardDTO updateAllStatusMaintenanceCard(Long id,String email,int role) throws NotFoundException, NotFoundRepairmanException, JsonProcessingException;
     boolean deleteMaintenanceCard(Long id) throws NotFoundException, NotFoundRepairmanException, NotEnoughProductException, UnknownException, JsonProcessingException;
     Map<String,Object> getMaintenanceCardByRepairMan(int PageNum, int PageSize, String sortBy, boolean descending,Long userId,String code,byte[] payStatus,byte[] workStatus);
     MaintenanceCardDTO setReturnDate(long id);
}
