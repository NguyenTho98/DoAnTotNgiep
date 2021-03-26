package com.sapo.qlsc.controller;

import com.sapo.qlsc.dto.CustomerDTO;
import com.sapo.qlsc.exception.customerException.DataTooLongException;
import com.sapo.qlsc.model.CustomerFilter;
import com.sapo.qlsc.model.SearchCustomer;
import com.sapo.qlsc.notification.MessageBox;
import com.sapo.qlsc.service.CustomerService;
import com.sapo.qlsc.service.MaintenanceCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8686")
@RequestMapping("/admin/")
public class CustomerController {

    private final CustomerService customerService;

    private final MaintenanceCardService maintenanceCardService;


    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) throws ParseException, DataTooLongException {
        CustomerDTO customer = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long idCustomer){
        CustomerDTO customerDTO = customerService.getById(idCustomer);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<Object> searchCustomer(@ModelAttribute("searchCustomer")SearchCustomer searchCustomer){
        Map<String,Object> allCustomer = customerService.searchCustomer(searchCustomer);
        return new ResponseEntity<>(allCustomer, HttpStatus.OK);

    }

    @GetMapping("/customers/filter")
    public ResponseEntity<Object> filterPayStatusOfCustomer(@ModelAttribute("CustomerFilter") CustomerFilter customerFilter){
        System.out.println(customerFilter);
        Map<String,Object> allCustomer = customerService.filterPayStatusOfCustomer(customerFilter);
        return new ResponseEntity<>(allCustomer, HttpStatus.OK);

    }

    @PutMapping("/customers/{idCustomer}")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("idCustomer") Long idCustomer){
        CustomerDTO customer = customerService.updateCustomer(customerDTO, idCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{idCustomer}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("idCustomer") Long idCustomer){
        customerService.deleteCustomer(idCustomer);
        return new ResponseEntity<>(new MessageBox(true, "xóa thành công"), HttpStatus.OK);
    }

    @DeleteMapping("/customers/updateStatus")
    public ResponseEntity<Object> updateMultipleStatusCustomer(
            @RequestParam(name = "ids", required = false, defaultValue = "") List<Long> ids){
        customerService.updateMultipleStatusCustomer(ids);
        return new ResponseEntity<>(new MessageBox(true, "xóa thành công"), HttpStatus.OK);
    }

}
