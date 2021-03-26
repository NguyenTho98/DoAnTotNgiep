package com.sapo.qlsc.service;

import com.sapo.qlsc.dto.CustomerDTO;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.exception.customerException.DataTooLongException;
import com.sapo.qlsc.model.CustomerFilter;
import com.sapo.qlsc.model.SearchCustomer;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerDTO addCustomer(CustomerDTO customerDTO) throws ParseException, DataTooLongException;
    Map<String, Object> searchCustomer(SearchCustomer searchCustomer);
    CustomerDTO updateCustomer(CustomerDTO customerDTO, Long idCustomer);
    void deleteCustomer(Long idCustomer);
    void updateMultipleStatusCustomer(List<Long> ids);
    CustomerDTO getById(Long idCustomer);
    Map<String, Object> filterPayStatusOfCustomer(CustomerFilter customerFilter);
    boolean checkPhoneNumber(String phoneNumber);
}
