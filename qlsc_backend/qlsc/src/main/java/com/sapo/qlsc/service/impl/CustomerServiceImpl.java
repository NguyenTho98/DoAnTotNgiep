package com.sapo.qlsc.service.impl;

import com.sapo.qlsc.converter.CustomerConverter;
import com.sapo.qlsc.converter.WardConverter;
import com.sapo.qlsc.dto.CustomerDTO;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.entity.Customer;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.exception.customerException.DataTooLongException;
import com.sapo.qlsc.exception.customerException.DupplicateFieldException;
import com.sapo.qlsc.exception.customerException.EntityNotFoundException;
import com.sapo.qlsc.model.CustomerFilter;
import com.sapo.qlsc.model.SearchCustomer;
import com.sapo.qlsc.repository.CustomerRepository;
import com.sapo.qlsc.repository.MaintenanceCardRepository;
import com.sapo.qlsc.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final WardConverter wardConverter;

    private final CustomerConverter customerConverter;

    private final MaintenanceCardRepository maintenanceCardRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, WardConverter wardConverter, CustomerConverter customerConverter, MaintenanceCardRepository maintenanceCardRepository) {
        this.customerRepository = customerRepository;
        this.wardConverter = wardConverter;
        this.customerConverter = customerConverter;
        this.maintenanceCardRepository = maintenanceCardRepository;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) throws ParseException, DataTooLongException {

        Customer customer = null;
        //
        if(customerDTO.getCode() == null || customerDTO.getCode().length() == 0){
            Long codeNumber = 0L;
            String newCodeString;
            int index = 0;
            String getMaxCode = null;
            do{
                getMaxCode = customerRepository.getMaxCode(index);
                //System.out.println(getMaxCode);
                if(getMaxCode == null){
                    getMaxCode = "0";
                }else{
                    boolean result = StringUtils.isNumeric(getMaxCode);
                    if(!result){
                        getMaxCode = null;
                        index++;
                    }else {
                        getMaxCode = getMaxCode;
                    }
                }
            }while (getMaxCode == null);
            codeNumber = Long.parseLong(getMaxCode) + 1;
            newCodeString = "kh00"+codeNumber.toString();
            Customer existedCode = customerRepository.findOneByCode(newCodeString);
            if(existedCode != null ) throw new DupplicateFieldException("Mã", "");
            customerDTO.setCode(newCodeString);
        }

        if(customerDTO.getCode() != null && customerDTO.getCode().length() > 0){
            Customer existedCode = customerRepository.findOneByCode(customerDTO.getCode());
            if(existedCode != null ) throw new DupplicateFieldException("Mã", "khách hàng ");
        }
        if(customerDTO.getPhoneNumber() != null && customerDTO.getPhoneNumber().length() >= 10){
            if(checkPhoneNumber(customerDTO.getPhoneNumber())){
                throw new DupplicateFieldException("Số điện thoại", "khách hàng ");
            }
        }

        customerDTO.setCreatedDate(new Date());
        customerDTO.setModifiedDate(new Date());
        customerDTO.setStatus((byte) 1);
        customerDTO.setCode(customerDTO.getCode().toLowerCase());
        customerDTO.setWard(customerDTO.getWard());
        customer = customerConverter.convertToEntity(customerDTO);

        try {
            customer = customerRepository.save(customer);
        }catch (DupplicateFieldException ex) {
            throw ex;
        } catch (Exception exception) {
            throw new DataTooLongException();
        }
        return customerConverter.convertToDTO(customer, "set");
    }

    @Override
    public Map<String, Object> searchCustomer(SearchCustomer searchCustomer) {

        int pageNumber = searchCustomer.getPage();
        int size = searchCustomer.getSize();
        String nameField = searchCustomer.getNameField();
        String order = searchCustomer.getOrder();
        String keyWork = searchCustomer.getSearch();
        Pageable paging = PageRequest.of(pageNumber - 1, size, Sort.by("modifiedDate").descending());

        if(nameField.equals("code")) {
            if(order.equals("ascend")){
                paging = PageRequest.of(pageNumber - 1, size, Sort.by("code"));
            }else if(order.equals("descend")){
                paging = PageRequest.of(pageNumber - 1, size, Sort.by("code").descending());
            }
        }

        else if(nameField.equals("name")){
            if(order.equals("ascend")){
                paging = PageRequest.of(pageNumber - 1, size, Sort.by("name"));
            }else if(order.equals("descend")){
                paging = PageRequest.of(pageNumber - 1, size, Sort.by("name").descending());
            }
        }

        Page<Customer> customerPage = customerRepository.search(paging, keyWork);
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        List<Customer> customers = customerPage.getContent();

        for (Customer customer : customers){
            customerDTOList.add(customerConverter.convertToDTO(customer, "get"));
        }
        map.put("customers", customerDTOList);
        map.put("currentPage", customerPage.getNumber() + 1);
        map.put("totalItems", customerPage.getTotalElements());
        map.put("totalPages", customerPage.getTotalPages());
        return map;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long idCustomer) {
        Customer customer = getCustomerById(idCustomer);
        customerDTO.setCreatedDate(new Date());
        customerDTO.setModifiedDate(new Date());
        customerDTO.setStatus((byte) 1);
        customerDTO.setCode(customerDTO.getCode().toLowerCase());

        if(!customerDTO.getCode().equals(customer.getCode())){
            Customer existedCode = customerRepository.findOneByCode(customerDTO.getCode());
            if(existedCode != null ) throw new DupplicateFieldException("Mã", "khách hàng ");
        }

        if(customerDTO.getCode() == null || customerDTO.getCode().length() == 0){
            Long codeNumber = 0L;
            String newCodeString;
            int index = 0;
            String getMaxCode = null;
            do{
                getMaxCode = customerRepository.getMaxCode(index);
                //System.out.println(getMaxCode);
                if(getMaxCode == null){
                    getMaxCode = "0";
                }else{
                    boolean result = StringUtils.isNumeric(getMaxCode);
                    if(!result){
                        getMaxCode = null;
                        index++;
                    }else {
                        getMaxCode = getMaxCode;
                    }
                }
            }while (getMaxCode == null);
            codeNumber = Long.parseLong(getMaxCode) + 1;
            newCodeString = "kh00"+codeNumber.toString();
            Customer existedCode = customerRepository.findOneByCode(newCodeString);
            if(existedCode != null ) throw new DupplicateFieldException("Mã", "khách hàng ");
            customerDTO.setCode(newCodeString.toLowerCase());
        }

        if(!customerDTO.getPhoneNumber().equals(customer.getPhoneNumber())){
            if(checkPhoneNumber(customerDTO.getPhoneNumber())){
                throw new DupplicateFieldException("Số điện thoại", "khách hàng ");
            }
        }
        customer.setCustomer(customerDTO);

        if(customerDTO.getWard() != null){
            customer.setWard(wardConverter.convertToEntity(customerDTO.getWard()));
        }else{
            customer.setWard(null);
        }

        try {
            customer = customerRepository.save(customer);
        }catch (DupplicateFieldException ex) {
            throw ex;
        } catch (Exception exception) {
            throw exception;
        }
        return customerConverter.convertToDTO(customer, "set");
    }

    @Override
    public void deleteCustomer(Long idCustomer) {
        Customer customer = getCustomerById(idCustomer);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO getById(Long idCustomer) {
        Customer customer = getCustomerById(idCustomer);
        if(customer.getStatus() == 0){
            throw new EntityNotFoundException(idCustomer, "customer");
        }
        return customerConverter.convertToDTO(customer, "get");
    }

    @Override
    public Map<String, Object> filterPayStatusOfCustomer(CustomerFilter customerFilter) {

        int pageNumber = customerFilter.getPage();
        int size = customerFilter.getSize();
        byte[] payStatus = customerFilter.getPayStatus();
        Pageable paging = PageRequest.of(pageNumber - 1, size, Sort.by("name"));

        Page<Customer> customerPage = customerRepository.filterPayStatusOfCustomer(paging, payStatus);
        System.out.println(customerPage.toString());
        List<CustomerDTO> customerDTO = new ArrayList<CustomerDTO>();

        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Customer> customers = customerPage.getContent();
        for (Customer customer : customers){
            customerDTO.add(customerConverter.convertToDTO(customer, "get"));
        }
        map.put("customers", customerDTO);
        map.put("currentPage", customerPage.getNumber() + 1);
        map.put("totalItems", customerPage.getTotalElements());
        map.put("totalPages", customerPage.getTotalPages());
        return map;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository.checkPhoneNumber(phoneNumber);
        return customer != null ? true : false;
    }

    private Customer getCustomerById(Long idCustomer){
        Optional<Customer> customer = customerRepository.findById(idCustomer);
        if(customer.isEmpty())
            throw new EntityNotFoundException(idCustomer, "customer");
        return customer.get();
    }

    @Override
    @Transactional
    public void updateMultipleStatusCustomer(List<Long> ids){
        try{
            for(Long id: ids){
                Customer customer = getCustomerById(id);
                customer.setStatus((byte) 0);
                customerRepository.save(customer);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
